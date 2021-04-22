package Parsing;

public class Num extends AST{

      private final int value;

    public Num(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
