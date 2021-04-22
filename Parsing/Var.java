package Parsing;

import Interpreting.UndefinedVariableException;

import java.util.HashMap;

public class Var extends AstNode {

    private final String value;

    public Var(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception{
        if(!variablesTable.containsKey(value))
            throw new UndefinedVariableException();

        return variablesTable.get(value);
    }
}
