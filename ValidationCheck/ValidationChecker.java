package ValidationCheck;

import java.util.ArrayList;

/**
 * ValidationChecker checks validity of current input line
 */
public class ValidationChecker {

    /**
     * Checks validity of current input line
     * @param words all the words of current input line
     * @throws InvalidExpressionException if the input line represents an invalid Java numeric expression
     */
    public void checkValidity(ArrayList<String> words) throws InvalidExpressionException{

        isAssignmentExpr(words);
        isAssignedExprLegal(words);

    }

    /**
     * Checks if the input line represents an assignment expression
     * @param words all the words of current input line
     * @throws InvalidExpressionException if the input line represents an invalid assignment expression
     */
    public void isAssignmentExpr(ArrayList<String> words) throws InvalidExpressionException{

        //assignment expression: variable =|+= expression

        if(!isVariable(words.get(0)))
            throw new IllegalVariableNameException();

        if(words.size() == 1 ||
                (!isAssign(words.get(1)) && !isPlusAssign(words.get(1))))
            throw new MissingAssignmentExpressionException();

        else{
            if(words.size() == 2 || !isAssignable(words.get(2)))
                throw new MissingAssignmentValueException();

        }
    }

    /**
     * Checks if the assigned expression is a valid assignable expression
     * @param words all the words of current input line
     * @throws InvalidExpressionException if the input line contains non assignable expression
     */
    public void isAssignedExprLegal(ArrayList<String> words) throws InvalidExpressionException{
        for(int i = 3; i < words.size(); i++){

            if(isBinaryOp(words.get(i))){
                //every binary operator must have two operands
                if(i == words.size()-1 || !isAssignable(words.get(i-1)) || !isAssignable(words.get(i+1)))
                    throw new MissingOperandException();
            }

            else if(isAssignable(words.get(i))){
                //every two operands must have an operator between them
                if(isAssignable(words.get(i-1)) ||
                        (i != words.size()-1 && isAssignable(words.get(i+1))))
                    throw new MissingOperatorException();
            }

            else
                throw new UnsupportedOperationException();

        }
    }

    /**
     * Checks if a given word represents an assignable value
     * @param s word
     * @return true if s represents an assignable value. Otherwise, returns false.
     */
    private boolean isAssignable(String s){
        return isVariable(s) || isNumber(s) || isPrefixInc(s) || isPostfixInc(s);
    }

    /**
     * Checks if a given word represents a variable
     */
    private boolean isVariable(String s){

        //a legal variable begins with an alphabetic letter, and consists of alphabetic letter and digits
        return s.matches("[a-zA-Z]+[a-zA-Z0-9]*");
    }

    /**
     * Checks if a given word represents a number
     */
    private boolean isNumber(String s){
        return s.matches("[0-9]+");
    }

    /**
     * Checks if a given word represents the assignment operator "="
     */
    private boolean isAssign(String s){
        return s.equals("=");
    }

    /**
     * Checks if a given word represents the plus assignment operator "+="
     */
    private boolean isPlusAssign(String s){
        return s.equals("+=");
    }

    /**
     * Checks if a given word represents a binary operator
     */
    private boolean isBinaryOp(String s){
        return s.equals("+") || s.equals("*");
    }

    /**
     * Checks if a given word represents a prefix increment expression
     */
    private boolean isPrefixInc(String s){

        //prefix increment expression: ++variable
        return s.matches("\\+\\+[a-zA-Z]+[a-zA-Z0-9]*");
    }

    /**
     * Checks if a given word represents a postfix increment expression
     */
    private boolean isPostfixInc(String s){

        //postfix increment expression: variable++
        return s.matches("[a-zA-Z]+[a-zA-Z0-9]*\\+\\+");
    }
}
