package ValidationCheck;

public class MissingAssignmentExpressionException extends InvalidExpressionException{

    private final static String msg = "Missing assignment expression";

    public String getMessage(){
        return msg;
    }
}
