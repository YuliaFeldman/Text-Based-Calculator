package ValidationCheck;

import java.util.ArrayList;

public class ValidationChecker {

    public void checkValidity(ArrayList<String> words) throws InvalidExpressionException{

        if(!isVariable(words.get(0)))
            throw new IllegalVariableNameException();

        if(words.size() == 1 ||
                (!isAssign(words.get(1)) && !isPlusAssign(words.get(1))))
            throw new MissingAssignmentExpressionException();

        else{
            if(words.size() == 2 || !isAssignable(words.get(2)))
                throw new MissingAssignmentValueException();

        }

        for(int i = 3; i < words.size(); i++){
            if(isBinaryOp(words.get(i))){
                if(i == words.size()-1 || !isAssignable(words.get(i-1)) || !isAssignable(words.get(i+1)))
                    throw new MissingOperandException();
            }

            else if(isAssignable(words.get(i))){
                if(isAssignable(words.get(i-1)) ||
                        (i != words.size()-1 && isAssignable(words.get(i+1))))
                    throw new MissingOperatorException();
            }
            else
                throw new UnsupportedOperationException();

        }


    }

    private boolean isAssignable(String s){
        return isVariable(s) || isNumber(s) || isPrefixInc(s) || isPostfixInc(s);
    }

    private boolean isVariable(String s){
        return s.matches("[a-zA-Z]+[a-zA-Z0-9]*");
    }

    private boolean isNumber(String s){
        return s.matches("[0-9]+");
    }

    private boolean isAssign(String s){
        return s.equals("=");
    }

    private boolean isPlusAssign(String s){
        return s.equals("+=");
    }

    private boolean isBinaryOp(String s){
        return s.equals("+") || s.equals("*");
    }

    private boolean isPrefixInc(String s){
        return s.matches("\\+\\+[a-zA-Z]+[a-zA-Z0-9]*");
    }

    private boolean isPostfixInc(String s){
        return s.matches("[a-zA-Z]+[a-zA-Z0-9]*\\+\\+");
    }
}
