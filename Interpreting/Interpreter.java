package Interpreting;

import Parsing.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Interpreter {

    private AstNode tree;
    private final HashMap<String, Integer> variablesTable;

    public Interpreter(){
        this.tree = null;
        variablesTable = new HashMap<>();
    }

    public void interpret(AstNode tree) throws Exception{
        //we assume the input is correct
        this.tree = tree;
        if(tree != null) {

            tree.evaluate(variablesTable);
        }
    }

    public void printResult() {
        if(variablesTable.isEmpty())
            System.out.println("()");

        else {
            String result = "(";
            Iterator iterator = variablesTable.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                result += pair.getKey() + "=" + pair.getValue() + ",";
                iterator.remove();
            }

            result = result.substring(0, result.length() - 1) + ")";
            System.out.println(result);
        }
    }
}
