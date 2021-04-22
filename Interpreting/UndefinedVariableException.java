package Interpreting;

public class UndefinedVariableException extends Exception{

    private final static String msg = "Invalid use of undefined variable";

    public String getMessage(){
        return msg;
    }
}
