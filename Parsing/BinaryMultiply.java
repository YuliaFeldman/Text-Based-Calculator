package Parsing;

import java.util.HashMap;

public class BinaryMultiply extends BinaryOp{

    public BinaryMultiply(AstNode left, String op, AstNode right){

        super(left, op, right);
    }

    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception {

        return left.evaluate(variablesTable) * right.evaluate(variablesTable);
    }
}
