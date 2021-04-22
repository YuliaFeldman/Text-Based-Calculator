package Interpreting;

public class UndefinedVariableException extends Exception{

    private final String msg = "Illegal use of undefined variable";

    public String getMessage(){
        return msg;
    }
}
