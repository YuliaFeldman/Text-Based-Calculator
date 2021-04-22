package Parsing;

public abstract class UnaryOp extends AstNode{

    protected final String op;
    protected final AstNode operand;

    public UnaryOp(String op, AstNode var){
        this.op = op;
        this.operand = var;
    }

    public String getOp(){
        return op;
    }

    public AstNode getOperand() { return operand; }

}
