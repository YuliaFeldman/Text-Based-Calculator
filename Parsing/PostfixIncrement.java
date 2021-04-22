package Parsing;

public class PostfixIncrement extends AST{

    private final String op;
    private final AST left;

    public PostfixIncrement(String op, AST left){
        this.op = op;
        this.left = left;
    }

    public AST getLeft(){
        return left;
    }

    public String getOp(){
        return op;
    }
}
