package exceptions;

/**
 * 空指针异常类
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