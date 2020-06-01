package domain.models;

public class Balance {

    private User oweTo;
    private float amount;
    private User user;

    public User getOweTo() {
        return oweTo;
    }

    public float getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public Balance(User oweTo, float amount, User user) {
        this.oweTo = oweTo;
        this.amount = amount;
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(user.getName()).append(" owes ")
                .append(oweTo.getName()).append(" ").append(amount);
        return stringBuilder.toString();
    }
}
