package Interpreting;

import Parsing.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Interpreter interprets the current parsed input line
 */
public class Interpreter {

    private final HashMap<String, Integer> variablesTable;

    /**
     * default constructor
     */
    public Interpreter(){
        variablesTable = new HashMap<>();
    }

    /**
     * Interprets the abstract syntax tree created while parsing the current input line
     * @param tree the root of the abstract syntax tree which represents the Java numeric expression
     * @throws Exception if interpretation process failed
     */
    public void interpret(AstNode tree) throws Exception{
        if(tree != null) {

            tree.evaluate(variablesTable);
        }
    }

    /**
     * Prints the value of each variable in the following format: (var1 = val1,var2 = val2, ...)
     */
    public void printResult() {

        if (!variablesTable.isEmpty()) {
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
