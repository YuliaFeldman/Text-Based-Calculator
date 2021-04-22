package Parsing;

import java.util.HashMap;

public class PrefixIncrement extends UnaryOp {

    public PrefixIncrement(String op, Var right){
        super(op, right);
    }

    public int evaluate(HashMap<String,Integer> variablesTable){

        String varName = ((Var)operand).getValue();
        int varValue = variablesTable.get(varName) + 1;
        variablesTable.replace(varName, varValue);
        return varValue;
    }
}
