package Parsing;

import Interpreting.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Parser will parse a single line and build an abstract syntax tree
 * which represents the expression this line holds
 * Example: if the expression is x = 1 + 2 * 3, then the parser will build the following tree:
 *
 *                  =
 *                /   \
 *              x      +
 *                   /  \
 *                 1     *
 *                      / \
 *                     2   3
 */
public class Parser {

    private ArrayList<String> words;
    private int currentIndex;

    /**
     * Constructor
     */
    public Parser(){
        words = new ArrayList<>();
        currentIndex = 0;
    }

    /**
     * Parses the current input line and creates an abstract syntax tree that represents the expression
     * @return root of the abstract syntax tree
     * @throws InvalidExpressionException if parsing process fails
     */
    public AstNode parse(String line) throws InvalidExpressionException {

        words = new ArrayList<>(Arrays.asList(line.split("[ \t]+")));
        currentIndex = 0;

        if(words.get(0).length() == 0) //if input line is empty
            return null;

        //validity check
        ValidationChecker checker = new ValidationChecker();
        checker.checkValidity(words);

        //start of parsing process
        if(words.get(1).equals("+="))
            return parsePlusAssignment();

        return parseAssignment();
    }


    /**
     * Parses an expression in the input line
     * @return the root of the subtree created by this function
     */
    private AstNode parseExpr(){

        //expr: term PLUS|MINUS term

        AstNode node = parseTerm();

        while(currentIndex < words.size() &&
                (words.get(currentIndex).equals("+") || words.get(currentIndex).equals("-"))){
            String op = words.get(currentIndex);
            currentIndex++;
            if(op.equals("+")) {
                node = new BinaryPlus(node, op, parseTerm());
            }
            else
                node = new BinaryMinus(node,op,parseTerm());
        }
        return node;
    }


    /**
     * Parses a term in the input line
     * @return the root of the subtree created by this function
     */
    private AstNode parseTerm(){

        //term: factor MUL factor

        AstNode node = parseFactor();

        while(currentIndex < words.size() && words.get(currentIndex).equals("*")){
            String op = words.get(currentIndex);
            currentIndex++;
            node = new BinaryMultiply(node, op, parseFactor());
        }
        return node;
    }


    /**
     * Parses a factor in the input line
     * @return the root of the subtree created by this function
     */
    private AstNode parseFactor(){

        //factor: number | variable | ++variable | variable++

        String currWord = words.get(currentIndex);

        if(currWord.matches("[0-9]+")){
            currentIndex++;
            return new Num(Integer.parseInt(currWord));
        }
        else if(currWord.length() > 1 && currWord.substring(0,2).equals("++"))
            return parsePrefixInc();

        else if(currWord.length() > 1 && currWord.substring(currWord.length()-2).equals("++"))
            return parsePostfixInc();

        return parseVariable();
    }


    /**
     * Parses assignment expression in the input line
     * @return the root of the tree created by this function
     */
    private AstNode parseAssignment(){

        //assignment: variable = expr

        AstNode left = parseVariable();
        currentIndex++;
        AstNode right = parseExpr();

        return new Assign(left, "=", right);
    }


    /**
     * Parses plus assignment expression in the input line
     * @return the root of the tree created by this function
     */
    private AstNode parsePlusAssignment(){

        //plus assignment: variable += expr

        AstNode left = parseVariable();
        currentIndex++;
        AstNode right = parseExpr();

        return new PlusAssign(left, "+=", right);

    }

    /**
     * Parses variable in the input line
     * @return the root of the subtree created by this function
     */
    private AstNode parseVariable(){
        AstNode var = new Var(words.get(currentIndex));
        currentIndex++;
        return var;
    }


    /**
     * Parses prefix increment expression in the input line
     * @return the root of the subtree created by this function
     */
    private AstNode parsePrefixInc(){

        //prefix increment: ++variable
        String varName = words.get(currentIndex).substring(2);
        Var var = new Var(varName);
        currentIndex++;
        return new PrefixIncrement("++", var);
    }

    /**
     * Parses postfix increment expression in the input line
     * @return the root of the subtree created by this function
     */
    private AstNode parsePostfixInc(){

        //postfix increment: variable++
        String varName = words.get(currentIndex).substring(0, words.get(currentIndex).length()-2);
        Var var = new Var(varName);
        currentIndex++;
        return new PostfixIncrement("++", var);
    }

}
