package constants;

public enum ExpenseType {

    EQUAL("equal"), EXACT("exact"), PERCENTAGE("percentage");

    private String value;

    public String getValue() {
        return value;
    }

    ExpenseType(String value){
        this.value = value;
    }

    public static ExpenseType getFromString(String text){
        for(ExpenseType e : ExpenseType.values()){
            if(e.getValue().equalsIgnoreCase(text)){
                return e;
            }
        }
        throw new IllegalArgumentException("Illegal value for enum provided");
    }

}
