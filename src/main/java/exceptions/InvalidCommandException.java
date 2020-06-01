package exceptions;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String message){
        super(message);
    }

    public InvalidCommandException(){
        super("Invalid command");
    }
}
