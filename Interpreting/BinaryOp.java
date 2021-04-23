package Interpreting;

/**
 * BinaryOp represents a binary operation node in the abstract syntax tree
 */
public abstract class BinaryOp extends AstNode {

    protected final AstNode left;
    protected final String op;
    protected final AstNode right;

    /**
     * Constructor
     * @param left left child node
     * @param op operation
     * @param right right child node
     */
    public BinaryOp(AstNode left, String op, AstNode right){
        this.op = op;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns left child node
     */
    public AstNode getLeft(){
        return left;
    }

    /**
     * Returns right child node
     */
    public AstNode getRight(){
        return right;
    }

    /**
     * Returns operation
     */
    public String getOp(){
        return op;
    }

}
