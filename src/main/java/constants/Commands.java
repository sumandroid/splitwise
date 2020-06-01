package constants;

public enum Commands {
    SHOW("show"),
    EXPENSE("expense");

    private String value;

    public String getValue(){
        return value;
    }

    Commands(String value){
        this.value = value;
    }

    public static Commands getFromString(String text){
        for(Commands c : Commands.values()){
            if(c.getValue().equalsIgnoreCase(text)){
                return c;
            }
        }
        throw new IllegalArgumentException("Illegal value for enum provided");
    }
}
