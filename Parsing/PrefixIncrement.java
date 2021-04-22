package Parsing;

public class PrefixIncrement extends AST{

    private final String op;
    private final AST right;

    public PrefixIncrement(String op, AST right){
        this.op = op;
        this.right = right;
    }

    public AST getRight(){
        return right;
    }

    public String getOp(){
        return op;
    }
}
