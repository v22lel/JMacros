package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.OperatorToken;

public class UnaryExpr extends Expression {
    private OperatorToken operator;
    private Expression expr;

    public UnaryExpr(OperatorToken operator, Expression expr) {
        this.operator = operator;
        this.expr = expr;
    }

    public OperatorToken getOperator() {
        return operator;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(operator);
        expr.toTokens(writer);
    }
}
