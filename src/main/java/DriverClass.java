import domain.models.Group;
import domain.models.User;
import processors.CommandProcessor;
import processors.CommandProcessorImpl;
import services.ExpenseService;
import services.ExpenseServiceImpl;

import java.util.Scanner;

public class DriverClass {

    public static void main(String[] args) {
        //creating some users
        User user1 = new User("user1", "suman", "sumansaurabh93s@gmail.com", "8505947133");
        User user2 = new User("user2", "sandeep", "sandeep@gmail.com", "8505947134");
        User user3 = new User("user3", "utkarsh", "utkarsh@gmail.com", "8505947135");
        User user4 = new User("user4", "nitin", "nitin@gmail.com", "8505947136");

        Group group = new Group("sobha mayflower");
        group.addUser(user1);
        group.addUser(user2);
        group.addUser(user3);
        group.addUser(user4);

        System.out.println("******Welcome to splitwise******");

        ExpenseService expenseService = new ExpenseServiceImpl(group);
        CommandProcessor commandProcessor = new CommandProcessorImpl(expenseService);

        while (true) {
            System.out.println("enter command");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            try {
                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting now.");
                    System.exit(0);
                } else {
                    commandProcessor.process(command);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
