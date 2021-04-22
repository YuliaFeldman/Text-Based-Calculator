package Interpreting;

import Parsing.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Interpreter {

    private AST tree;
    private final HashMap<String, Integer> variablesTable;

    public Interpreter(){
        this.tree = null;
        variablesTable = new HashMap<>();
    }

    public void interpret(AST tree){
        //we assume the input is correct
        this.tree = tree;
        if(tree != null) {
            String varName = ((Var) ((Assign) tree).getLeft()).getValue();
            int varValue = visitNode(((Assign) tree).getRight());
            variablesTable.put(varName, varValue);
        }
    }

    private int visitNode(AST node){
        if(node.getClass().getName().equals("Parsing.BinaryOp"))
            return visitBinaryOp(node);

        else if(node.getClass().getName().equals("Parsing.Num"))
            return visitNum(node);

        else if(node.getClass().getName().equals("Parsing.PrefixIncrement"))
            return visitPrefixIncrement(node);

        else if(node.getClass().getName().equals("Parsing.PostfixIncrement"))
            return visitPostfixIncrement(node);

        return visitVariable(node);
    }

    private int visitBinaryOp(AST node){
        String op = ((BinaryOp)node).getOp();

        if(op.equals("+"))
            return visitNode(((BinaryOp)node).getLeft()) + visitNode(((BinaryOp)node).getRight());

        //else if(op.equals("*"))
        return visitNode(((BinaryOp)node).getLeft()) * visitNode(((BinaryOp)node).getRight());
    }

    private int visitNum(AST node){
        return ((Num)node).getValue();
    }

    private int visitVariable(AST node){
        String varName = ((Var)node).getValue();
        return variablesTable.get(varName);
        //for now I assume that every variable used was assigned first
    }

    private int visitPrefixIncrement(AST node){
        String varName = ((Var)((PrefixIncrement)node).getRight()).getValue();
        int varValue = variablesTable.get(varName) + 1;
        variablesTable.replace(varName, varValue);
        return varValue;
    }

    private int visitPostfixIncrement(AST node){
        String varName = ((Var)((PostfixIncrement)node).getLeft()).getValue();     //   System.out.println(varName);
        int varValue = variablesTable.get(varName) + 1;
        variablesTable.replace(varName, varValue);
        return varValue - 1;
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
