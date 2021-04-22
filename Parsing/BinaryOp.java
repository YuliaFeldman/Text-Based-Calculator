package Parsing;

public abstract class BinaryOp extends AstNode {

    protected final AstNode left;
    protected final String op;
    protected final AstNode right;

    public BinaryOp(AstNode left, String op, AstNode right){
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public AstNode getLeft(){
        return left;
    }

    public AstNode getRight(){
        return right;
    }

    public String getOp(){
        return op;
    }

}
