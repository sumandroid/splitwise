package strategies;

import domain.models.Balance;
import domain.models.User;

import java.util.ArrayList;
import java.util.List;

public class ExactSplitStartegy implements SplitStrategy {
    @Override
    public List<Balance> splitAmount(float amount, User payer, List<User> users, List<String> shares) {
        if(amount <= 0) throw new IllegalArgumentException("amount is less than equal to zero");
        if(users.size() != shares.size()) throw new IllegalArgumentException("user and shares are not equal for exact split");
        List<Balance> balances = new ArrayList<>();
        for(int i = 0; i < users.size(); i++){
            Balance balance = new Balance(payer, Float.parseFloat(shares.get(i)), users.get(i));
            balances.add(balance);
        }
        return balances;
    }
}
