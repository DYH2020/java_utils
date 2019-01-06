package exceptions;

/**
 * Created by huangguoping.
 */
public class NullOrEmptyException extends Exception {
    private String message = "";
    public NullOrEmptyException(){}
    public NullOrEmptyException(String msg){
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}