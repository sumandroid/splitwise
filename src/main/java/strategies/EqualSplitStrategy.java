package strategies;

import domain.models.Balance;
import domain.models.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public List<Balance> splitAmount(float amount, User payer, List<User> users, List<String> shares) {
        if(amount <= 0) throw new IllegalArgumentException("amount is less than equal to zero");
        List<Balance> balances = new ArrayList<>();
        float share = Math.round(amount / (float) users.size());
        for(User user : users){
            if(user.getUserId() != payer.getUserId()){
                Balance balance = new Balance(payer, share, user);
                balances.add(balance);
            }
        }
        return balances;
    }
}
