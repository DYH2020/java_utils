package exceptions;

public class AppMessageException extends Exception {
    public AppMessageException(){}
    public AppMessageException(String msg){
        super(msg);
    }
}
