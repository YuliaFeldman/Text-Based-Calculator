package Parsing;

import java.util.HashMap;

public class BinaryPlus extends BinaryOp{

    public BinaryPlus(AstNode left, String op, AstNode right){

        super(left, op, right);
    }

    public int evaluate(HashMap<String,Integer> variablesTable) throws Exception {

        return left.evaluate(variablesTable) + right.evaluate(variablesTable);
    }
}
