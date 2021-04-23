package Parsing;

public class MultiAssignmentException extends InvalidExpressionException{

    private final static String msg = "Only one assignment is allowed in expression";

    public String getMessage(){
        return msg;
    }
}
