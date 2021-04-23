package Interpreting;

import java.util.HashMap;

/**
 * UPostfixIncrement represents a postfix increment operation node in the abstract syntax tree
 */
public class PostfixIncrement extends UnaryOp {

    /**
     * Constructor
     * @param op operator
     * @param left left operand
     */
    public PostfixIncrement(String op, Var left){
        super(op, left);
    }

    /**
     * Evaluates the value of this postfix increment operation node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     */
    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception{

        String varName = ((Var)operand).getValue();
        int varValue = operand.evaluate(variablesTable) + 1;
        variablesTable.replace(varName, varValue);
        return varValue - 1;
    }
}
