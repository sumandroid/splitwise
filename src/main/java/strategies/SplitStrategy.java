package strategies;

import domain.models.Balance;
import domain.models.User;

import java.util.List;

public interface SplitStrategy {

    List<Balance> splitAmount(float amount, User payer, List<User> users, List<String> shares);
}
