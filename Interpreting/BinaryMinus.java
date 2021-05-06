package Interpreting;

import java.util.HashMap;

/**
 * BinaryPlus represents a binary minus operation node in the abstract syntax tree
 */
public class BinaryMinus extends BinaryOp{

    /**
     * Constructor
     * @param left left child node
     * @param op operation
     * @param right right child node
     */
    public BinaryMinus(AstNode left, String op, AstNode right){

        super(left, op, right);
    }

    /**
     * Evaluates the value of this binary minus operation node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     * @throws Exception if evaluation process fails
     */
    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception {

        return left.evaluate(variablesTable) - right.evaluate(variablesTable);
    }
}
