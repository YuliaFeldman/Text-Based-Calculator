package Interpreting;

import java.util.HashMap;


/**
 * Assign represents a binary plus assignment operation node in the abstract syntax tree
 */
public class PlusAssign extends BinaryOp {

    /**
     * Constructor
     * @param left left child node
     * @param op operation
     * @param right right child node
     */
    public PlusAssign(AstNode left, String op, AstNode right){

        super(left, op, right);
    }

    /**
     * Evaluates the value of this binary plus assignment operation node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     * @throws Exception if evaluation process fails
     */
    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception {

        String varName = ((Var)left).getValue();
        int leftOperandValue = left.evaluate(variablesTable);
        int rightOperandValue = right.evaluate(variablesTable);
        int varValue = leftOperandValue + rightOperandValue;
        variablesTable.put(varName, varValue);
        return varValue;
    }
}
