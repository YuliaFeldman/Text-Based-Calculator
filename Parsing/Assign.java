package Parsing;

import java.util.HashMap;

public class Assign extends BinaryOp{

    public Assign(AstNode left, String op, AstNode right){

        super(left, op, right);
    }

    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception {

        String varName = ((Var)left).getValue();
        int varValue = right.evaluate(variablesTable);
        variablesTable.put(varName, varValue);
        return varValue;
    }
}

