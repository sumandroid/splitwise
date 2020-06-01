package domain.models;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private List<User> users;
    private List<Expense> expenses;

    public List<User> getUsers() {
        return users;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    public Group(String name){
        this.name = name;
        this.users = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }
}
