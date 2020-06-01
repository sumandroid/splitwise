package services;

import constants.ExpenseType;
import domain.models.Balance;
import domain.models.Expense;
import domain.models.Group;
import domain.models.User;
import factories.StrategyFactory;
import strategies.SplitStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private Group group;
    private UserService userService;
    private static final String NO_BALANCE = "No balances";
    private static final String SPLITTER = "#";

    public ExpenseServiceImpl(Group group) {
        this.group = group;
        this.userService = new UserServiceImpl(group);
    }

    @Override
    public String show() {
        HashMap<String, List<Balance>> userBalanceMap = groupBalanceByOweToPaidBy(group);
        if (userBalanceMap.size() == 0) {
            return NO_BALANCE;
        }
        List<Balance> balances = generateAggregatedBalanceList(userBalanceMap);
        StringBuilder stringBuilder = new StringBuilder();
        for (Balance balance : balances) {
            stringBuilder.append(balance.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String showBalanceForUser(String userId) {
        List<Balance> balances;
        User user = userService.getById(userId);
        HashMap<String, List<Balance>> userBalanceMap = groupBalanceByUserId(group, user);
        balances = generateAggregatedBalanceList(userBalanceMap);
        if (balances.size() == 0) return NO_BALANCE;
        StringBuilder stringBuilder = new StringBuilder();
        for (Balance balance : balances) {
            stringBuilder.append(balance.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void createExpense(String payerId, float amount, Integer numberOfUsers,
                              List<String> userIds, ExpenseType expenseType, List<String> shares) {
        List<User> users = new ArrayList<>();
        SplitStrategy splitStrategy = StrategyFactory.getSplitStrategy(expenseType);
        User payer = userService.getById(payerId);
        Expense expense = new Expense("expense-" + amount, amount,
                payer, expenseType);
        userIds.forEach(id -> users.add(userService.getById(id)));
        List<Balance> balances = splitStrategy.splitAmount(amount, payer, users, shares);
        expense.addBalance(payer, balances);
        group.addExpense(expense);
    }


    private HashMap<String, List<Balance>> groupBalanceByOweToPaidBy(Group group) {
        HashMap<String, List<Balance>> userBalanceMap = new HashMap<>();
        group.getExpenses().forEach(expense -> {
            for (List<Balance> list : expense.getUserBalanceHashMap().values()) {
                for (Balance balance : list) {
                    User oweTo = balance.getOweTo();
                    User user = balance.getUser();
                    String key = oweTo.getUserId() + SPLITTER + user.getUserId();
                    if (!userBalanceMap.containsKey(key)) {
                        userBalanceMap.put(key, new ArrayList<>());
                    }
                    userBalanceMap.get(key).add(balance);
                }
            }
        });
        return userBalanceMap;
    }

    private HashMap<String, List<Balance>> groupBalanceByUserId(Group group, User user) {
        HashMap<String, List<Balance>> allUsersBalanceMap = groupBalanceByOweToPaidBy(group);
        HashMap<String, List<Balance>> userBalanceMap = new HashMap<>();
        for (String key : allUsersBalanceMap.keySet()) {
            String[] tokens = key.split(SPLITTER);
            if (tokens[0].equalsIgnoreCase(user.getUserId()) || tokens[1].equalsIgnoreCase(user.getUserId())) {
                userBalanceMap.put(key, allUsersBalanceMap.get(key));
            }
        }
        return userBalanceMap;
    }

    private List<Balance> generateAggregatedBalanceList(HashMap<String, List<Balance>> userBalanceMap) {
        List<Balance> balances = new ArrayList<>();
        for (String key : userBalanceMap.keySet()) {
            List<Balance> balanceList = userBalanceMap.get(key);
            float amount = balanceList.stream().map(Balance::getAmount).reduce(0f, Float::sum);
            if (amount > 0) {
                Balance balance = new Balance(balanceList.get(0).getOweTo(), amount, balanceList.get(0).getUser());
                balances.add(balance);
            }
        }
        return balances;
    }
}
