package Interpreting;

import java.util.HashMap;

/**
 * Num represents a number node in the abstract syntax tree
 */
public class Num extends AstNode {

    private final int value;

    /**
     * Constructor
     * @param value value of this number node
     */
    public Num(int value){
        this.value = value;
    }

    /**
     * @return value of this number node
     */
    public int getValue(){
        return value;
    }

    /**
     * Evaluates the value of this number node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     */
    public int evaluate(HashMap<String,Integer> variablesTable){
        return value;
    }
}
