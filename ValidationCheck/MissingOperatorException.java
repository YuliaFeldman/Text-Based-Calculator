package ValidationCheck;

public class MissingOperatorException extends InvalidExpressionException{

    private final static String msg = "Missing operator exception";

    public String getMessage(){
        return msg;
    }
}
