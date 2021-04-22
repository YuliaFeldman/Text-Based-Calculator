package Parsing;

import java.util.HashMap;

public class Num extends AstNode {

      private final int value;

    public Num(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public int evaluate(HashMap<String,Integer> variablesTable){
        return value;
    }
}
