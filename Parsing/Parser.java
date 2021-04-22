package Parsing;

import ValidationCheck.InvalidExpressionException;
import ValidationCheck.ValidationChecker;

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

    private final ArrayList<String> words;
    private int currentIndex;

    /**
     * Constructor
     * @param line current input line
     */
    public Parser(String line){
        words = new ArrayList<>(Arrays.asList(line.split("[ \t]+")));
        currentIndex = 0;
    }

    /**
     * Parses the current input line and creates an abstract syntax tree that represents the expression
     * @return root of the abstract syntax tree
     * @throws InvalidExpressionException if parsing process fails
     */
    public AstNode parse() throws InvalidExpressionException {

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
     *
     * @return
     */
    private AstNode parseExpr(){

        //expr: term PLUS term

        AstNode node = parseTerm();

        while(currentIndex < words.size() && words.get(currentIndex).equals("+")){
            String op = words.get(currentIndex);
            currentIndex++;
            node = new BinaryPlus(node, op, parseTerm());
        }
        return node;
    }

    //term: factor MUL factor
    /**
     *
     * @return
     */
    private AstNode parseTerm(){
        AstNode node = parseFactor();

        while(currentIndex < words.size() && words.get(currentIndex).equals("*")){
            String op = words.get(currentIndex);
            currentIndex++;
            node = new BinaryMultiply(node, op, parseFactor());
        }
        return node;
    }

    //factor: number | variable | ++variable | variable++
    /**
     *
     * @return
     */
    private AstNode parseFactor(){
        String currWord = words.get(currentIndex);

        if(currWord.matches("[0-9]+")){
            currentIndex++;
            return new Num(Integer.valueOf(currWord));
        }
        else if(currWord.length() > 1 && currWord.substring(0,2).equals("++"))
            return parsePrefixInc();

        else if(currWord.length() > 1 && currWord.substring(currWord.length()-2).equals("++"))
            return parsePostfixInc();

        return parseVariable();
    }

    //assignment: variable = expr
    /**
     *
     * @return
     */
    private AstNode parseAssignment(){
        AstNode left = parseVariable();
        currentIndex++;
        AstNode right = parseExpr();

        return new Assign(left, "=", right);
    }

    //plusAssignment: variable += expr
    /**
     *
     * @return
     */
    private AstNode parsePlusAssignment(){
        words.set(1, "=");
        words.add(2, words.get(0));
        words.add(3, "+");
        return parseAssignment();
    }

    /**
     *
     * @return
     */
    private AstNode parseVariable(){
        AstNode var = new Var(words.get(currentIndex));
        currentIndex++;
        return var;
    }

    /**
     *
     * @return
     */
    private AstNode parsePrefixInc(){
        String varName = words.get(currentIndex).substring(2);
        Var var = new Var(varName);
        currentIndex++;
        return new PrefixIncrement("++", var);
    }

    /**
     *
     * @return
     */
    private AstNode parsePostfixInc(){
        String varName = words.get(currentIndex).substring(0, words.get(currentIndex).length()-2);
        Var var = new Var(varName);
        currentIndex++;
        return new PostfixIncrement("++", var);
    }

}
