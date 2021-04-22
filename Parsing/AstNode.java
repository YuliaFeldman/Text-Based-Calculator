package Parsing;

import java.util.HashMap;

public abstract class AstNode {

    public abstract int evaluate(HashMap<String,Integer> variablesTable) throws Exception;
}
