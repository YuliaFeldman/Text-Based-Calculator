package ValidationCheck;

public class MissingOperandException extends InvalidExpressionException{

    private final String msg = "Missing operand exception";

    public String getMessage(){
        return msg;
    }
}
