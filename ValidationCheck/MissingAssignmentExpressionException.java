package ValidationCheck;

public class MissingAssignmentExpressionException extends InvalidExpressionException{

    private final String msg = "Missing assignment expression";

    public String getMessage(){
        return msg;
    }
}
