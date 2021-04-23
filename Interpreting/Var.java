package Interpreting;

import Interpreting.AstNode;
import Interpreting.UndefinedVariableException;

import java.util.HashMap;

/**
 * Var represents a variable node in the abstract syntax tree
 */
public class Var extends AstNode {

    private final String value; //name of variable

    /**
     * Constructor
     * @param value name of variable
     */
    public Var(String value){
        this.value = value;
    }

    /**
     * @return value
     */
    public String getValue(){
        return value;
    }

    /**
     * Evaluates the value of this variable node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     * @throws Exception if evaluation process fails
     */
    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception{
        if(!variablesTable.containsKey(value))
            throw new UndefinedVariableException();

        return variablesTable.get(value);
    }
}
