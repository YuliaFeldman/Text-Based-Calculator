package Parsing;

import java.util.HashMap;

public class PostfixIncrement extends UnaryOp {

    public PostfixIncrement(String op, Var left){
        super(op, left);
    }

    public int evaluate(HashMap<String,Integer> variablesTable){

        String varName = ((Var)operand).getValue();
        int varValue = variablesTable.get(varName) + 1;
        variablesTable.replace(varName, varValue);
        return varValue - 1;
    }
}
