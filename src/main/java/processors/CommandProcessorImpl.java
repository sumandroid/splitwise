package processors;

import constants.Commands;
import constants.ExpenseType;
import domain.models.Expense;
import domain.models.Group;
import exceptions.InvalidCommandException;
import services.ExpenseService;

import java.util.Arrays;
import java.util.List;

public class CommandProcessorImpl implements CommandProcessor {

    private ExpenseService expenseService;

    public CommandProcessorImpl(ExpenseService expenseService){
        this.expenseService = expenseService;
    }


    @Override
    public void process(String input) {
        String[] tokens = input.trim().split(" ");
        if(tokens.length == 0) throw new InvalidCommandException();
        Commands command = Commands.getFromString(tokens[0]);
        switch (command){
            case SHOW:
                if(tokens.length == 1) {
                    printResult(expenseService.show());
                }else{
                    if(tokens.length > 2) throw new InvalidCommandException("invalid show command");
                    printResult(expenseService.showBalanceForUser(tokens[1]));
                }
                break;
            case EXPENSE:
                List<String> commandList = Arrays.asList(tokens);
                String payerId = commandList.get(1);
                float amount = Float.parseFloat(commandList.get(2));
                Integer numberOfUsers = Integer.valueOf(commandList.get(3));
                List<String> userIds = commandList.subList(4, 4 + numberOfUsers);
                ExpenseType expenseType = constants.ExpenseType.getFromString(commandList.get(3 + numberOfUsers + 1));
                List<String> shares = commandList.subList(3 + numberOfUsers + 2, commandList.size());
                expenseService.createExpense(payerId, amount, numberOfUsers, userIds, expenseType, shares);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }

    }

    private void printResult(String result){
        System.out.println(result);
    }
}
