package ValidationCheck;

public class MissingAssignmentValueException extends InvalidExpressionException{

    private final static String msg = "Missing assignment value";

    public String getMessage(){
        return msg;
    }
}
