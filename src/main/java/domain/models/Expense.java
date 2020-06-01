package domain.models;

import constants.ExpenseType;

import java.util.HashMap;
import java.util.List;

public class Expense {
    private String name;
    private float amount;
    private User paidBy;
    private ExpenseType expenseType;
    private HashMap<User, List<Balance>> userBalanceHashMap;

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public HashMap<User, List<Balance>> getUserBalanceHashMap() {
        return userBalanceHashMap;
    }

    public void addBalance(User payer, List<Balance> balances){
        userBalanceHashMap.put(payer, balances);
    }

    public Expense(String name, float amount, User paidBy, ExpenseType expenseType) {
        this.name = name;
        this.amount = amount;
        this.paidBy = paidBy;
        this.expenseType = expenseType;
        this.userBalanceHashMap = new HashMap<>();
    }


}
