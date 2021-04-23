package Interpreting;

import java.util.LinkedHashMap;


/**
 * Interpreter interprets the current parsed input line
 */
public class Interpreter {

    private final LinkedHashMap<String, Integer> variablesTable;

    /**
     * default constructor
     */
    public Interpreter(){
        variablesTable = new LinkedHashMap<>();
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
     * @return the values of each variable
     */
    public String printResult() {

        String result = "";
        if (!variablesTable.isEmpty()) {
            result = "(";
            for(String key: variablesTable.keySet())
                result =  result.concat( key + "=" + variablesTable.get(key) + ",");

            result = result.substring(0, result.length() - 1) + ")";
            System.out.println(result);
        }
        return result;
    }
}
