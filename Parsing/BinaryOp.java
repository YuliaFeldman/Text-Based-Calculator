package Parsing;

public class BinaryOp extends AST{

    private final AST left;
    private final String op;
    private final AST right;

    public BinaryOp(AST left, String op, AST right){
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public AST getLeft(){
        return left;
    }

    public AST getRight(){
        return right;
    }

    public String getOp(){
        return op;
    }
}
