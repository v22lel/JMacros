package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;

public class AssignmentExpr extends Expression {
    private Expression left;
    private Expression right;

    public AssignmentExpr(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    @Override
    public void toTokens(TokenStream writer) {
        left.toTokens(writer);
        writer.pushSingle(new Token.Eq());
        right.toTokens(writer);
    }
}
