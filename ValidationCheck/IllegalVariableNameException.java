package ValidationCheck;

public class IllegalVariableNameException extends InvalidExpressionException{

    private final String msg = "Name of variable is illegal";

    public String getMessage(){
        return msg;
    }
}
