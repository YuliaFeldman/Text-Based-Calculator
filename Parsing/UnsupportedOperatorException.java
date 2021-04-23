package Parsing;

public class UnsupportedOperatorException extends InvalidExpressionException{
    private final static String msg = "Invalid use of unsupported operator";

    public String getMessage(){
        return msg;
    }
}
