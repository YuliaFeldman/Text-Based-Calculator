package Parsing;

import ValidationCheck.InvalidExpressionException;
import ValidationCheck.ValidationChecker;

import java.util.ArrayList;
import java.util.Arrays;

//Parsing.Parser will parse a single line and build an abstract syntax tree
//which represents the expression this line holds
public class Parser {

    private final ArrayList<String> words;
    private int currentIndex;

    public Parser(String line){
        words = new ArrayList<>(Arrays.asList(line.split("[ \t]+")));
        currentIndex = 0;
    }


    public AST parse() throws InvalidExpressionException {
        if(words.get(0).length() == 0)
            return null;

        ValidationChecker checker = new ValidationChecker();
        checker.checkValidity(words);

        if(words.get(1).equals("+="))
            return parsePlusAssignment();

        return parseAssignment();
    }

    //expr: term PLUS term
    private AST parseExpr(){
        AST node = parseTerm();

        if(currentIndex < words.size()) {
            String op = words.get(currentIndex);

            while (op.equals("+") && currentIndex < words.size()) {
//                System.out.println("expr: " + op);
                currentIndex++;

                node = new BinaryOp(node, "+", parseTerm());
                if(currentIndex < words.size())
                    op = words.get(currentIndex);
            }
        }
        return node;
    }

    //term: factor MUL factor
    private AST parseTerm(){
        AST node = parseFactor();

        if(currentIndex < words.size()) {
            String op = words.get(currentIndex);

            while (op.equals("*") && currentIndex < words.size()) {
//                System.out.println("term:" + op);
                currentIndex++;

                node = new BinaryOp(node, "*", parseFactor());
                if(currentIndex < words.size())
                    op = words.get(currentIndex);
            }
        }
        return node;
    }

    //factor: INTEGER | variable | ++variable | variable++
    private AST parseFactor(){
        String currWord = words.get(currentIndex);

        if(currWord.matches("[0-9]+")){
//            System.out.println("factor: "+ Integer.valueOf(currWord));
            currentIndex++;
            return new Num(Integer.valueOf(currWord));
        }
        else if(currWord.length() > 1 && currWord.substring(0,2).equals("++"))
            return parsePrefixInc();

        else if(currWord.length() > 1 && currWord.substring(currWord.length()-2).equals("++"))
            return parsePostfixInc();

        return parseVariable();
    }

    private AST parseAssignment(){
        AST left = parseVariable();
        currentIndex++;
        AST right = parseExpr();

        return new Assign(left, "=", right);
    }

    //for parsing "+="
    private AST parsePlusAssignment(){
        words.set(1, "=");
        words.add(2, words.get(0));
        words.add(3, "+");
        return parseAssignment();
    }

    //this is the same as parseFactor.. can be united
    private AST parseVariable(){
//        System.out.println("variable: "+ tokens[currentIndex]);
        AST var = new Var(words.get(currentIndex));
        currentIndex++;
        return var;
    }

    private AST parsePrefixInc(){
        String varName = words.get(currentIndex).substring(2);
        AST var = new Var(varName);
        currentIndex++;
        return new PrefixIncrement("++", var);
    }

    private AST parsePostfixInc(){
        String varName = words.get(currentIndex).substring(0, words.get(currentIndex).length()-2);
        AST var = new Var(varName);
        currentIndex++;
        return new PostfixIncrement("++", var);
    }

}
