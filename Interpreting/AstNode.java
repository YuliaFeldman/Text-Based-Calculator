package Interpreting;

import java.util.HashMap;

/**
 * AstNode represents a node in the tree created from parsing a Java numeric assignment expression
 */
public abstract class AstNode {

    /**
     *
     * Evaluates the value of this node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     * @throws Exception if evaluation process fails
     */
    public abstract int evaluate(HashMap<String,Integer> variablesTable) throws Exception;
}
