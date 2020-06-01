package services;

import constants.ExpenseType;
import domain.models.Group;

import java.util.List;

public interface ExpenseService {

    String show();

    String showBalanceForUser(String userId);

    void createExpense(String payerId, float amount, Integer numberOfUsers,
                       List<String> userIds, ExpenseType expenseType, List<String> shares);
}
