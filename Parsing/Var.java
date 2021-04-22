package Parsing;

public class Var extends AST{

    private final String value;

    public Var(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
