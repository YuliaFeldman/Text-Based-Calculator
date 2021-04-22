package ValidationCheck;

public class MissingOperatorException extends InvalidExpressionException{

    private final String msg = "Missing operator exception";

    public String getMessage(){
        return msg;
    }
}
