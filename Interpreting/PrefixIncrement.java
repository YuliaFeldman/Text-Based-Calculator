package Interpreting;

import java.util.HashMap;

/**
 * PrefixIncrement represents a prefix increment operation node in the abstract syntax tree
 */
public class PrefixIncrement extends UnaryOp {

    /**
     * Constructor
     * @param op operator
     * @param right right operand
     */
    public PrefixIncrement(String op, Var right){
        super(op, right);
    }

    /**
     * Evaluates the value of this prefix increment operation node according to values of the variables
     * @param variablesTable values of variables
     * @return the value of this node
     */
    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception{

        String varName = ((Var)operand).getValue();
        int varValue = operand.evaluate(variablesTable) + 1;
        variablesTable.replace(varName, varValue);
        return varValue;
    }
}
