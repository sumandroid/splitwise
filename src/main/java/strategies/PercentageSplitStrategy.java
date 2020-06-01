package strategies;

import domain.models.Balance;
import domain.models.User;

import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy {

    @Override
    public List<Balance> splitAmount(float amount, User payer, List<User> users, List<String> percentages) {
        if(amount <= 0) throw new IllegalArgumentException("amount is less than equal to zero");
        if(users.size() != percentages.size()) throw new IllegalArgumentException("users and percentages are not equal for exact split");
        List<Balance> balances = new ArrayList<>();
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserId() != payer.getUserId()){
                float percentage = Float.parseFloat(percentages.get(i));
                float share = amount * (percentage / 100.0f);
                Balance balance = new Balance(payer, share, users.get(i));
                balances.add(balance);
            }
        }
        return balances;
    }
}
