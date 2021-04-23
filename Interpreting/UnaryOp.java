package Interpreting;

import Interpreting.AstNode;

/**
 * UnaryOp represents an Unary operation node in the abstract syntax tree
 */
public abstract class UnaryOp extends AstNode {

    protected final String op;
    protected final AstNode operand;

    /**
     * Constructor
     * @param op operation
     * @param var variable
     */
    public UnaryOp(String op, AstNode var){
        this.op = op;
        this.operand = var;
    }

    /**
     * @return operation
     */
    public String getOp(){
        return op;
    }

    /**
     * @return operand
     */
    public AstNode getOperand() { return operand; }

}
