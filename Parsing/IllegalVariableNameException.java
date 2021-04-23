package Parsing;

public class IllegalVariableNameException extends InvalidExpressionException{

    private final static String msg = "Name of variable is illegal";

    public String getMessage(){
        return msg;
    }
}
