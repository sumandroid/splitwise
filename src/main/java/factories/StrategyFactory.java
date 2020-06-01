package factories;

import constants.ExpenseType;
import strategies.EqualSplitStrategy;
import strategies.ExactSplitStartegy;
import strategies.PercentageSplitStrategy;
import strategies.SplitStrategy;

public class StrategyFactory {

    public static SplitStrategy getSplitStrategy(ExpenseType expenseType){
        switch (expenseType){
            case EQUAL:
                return new EqualSplitStrategy();
            case EXACT:
                return new ExactSplitStartegy();
            case PERCENTAGE:
                return new PercentageSplitStrategy();
            default:
                throw new IllegalArgumentException("illegal expense type");
        }
    }
}
