package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.OperatorToken;

public class BinaryExpr extends Expression {
    private OperatorToken operator;
    private Expression left, right;

    public BinaryExpr(OperatorToken operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public OperatorToken getOperator() {
        return operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public void toTokens(TokenStream writer) {
        left.toTokens(writer);
        writer.pushSingle(operator);
        right.toTokens(writer);
    }
}
