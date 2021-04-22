package Parsing;

import java.util.HashMap;

/**
 * Assign represents a binary assignment operation node in the abstract syntax tree
 */
public class Assign extends BinaryOp{

    /**
     * Constructor
     * @param left left child node
     * @param op operation
     * @param right right child node
     */
    public Assign(AstNode left, String op, AstNode right){

        super(left, op, right);
    }

    /**
     * Evaluates the value of this binary assignment operation node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     * @throws Exception if evaluation process fails
     */
    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception {

        String varName = ((Var)left).getValue();
        int varValue = right.evaluate(variablesTable);
        variablesTable.put(varName, varValue);
        return varValue;
    }
}

