package Interpreting;

import java.util.HashMap;

/**
 * BinaryMultiply represents a binary multiply operation node in the abstract syntax tree
 */
public class BinaryMultiply extends BinaryOp{

    /**
     * Constructor
     * @param left left child node
     * @param op operation
     * @param right right child node
     */
    public BinaryMultiply(AstNode left, String op, AstNode right){

        super(left, op, right);
    }

    /**
     * Evaluates the value of this binary multiply operation node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     * @throws Exception if evaluation process fails
     */
    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception {

        return left.evaluate(variablesTable) * right.evaluate(variablesTable);
    }
}
