package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.OperatorToken;

//++a or --a
public class PrefixExpr extends Expression{
    private OperatorToken operator;
    private Expression expr;

    public PrefixExpr(OperatorToken operator, Expression expr) {
        this.operator = operator;
        this.expr = expr;
    }

    public OperatorToken getOperator() {
        return operator;
    }

    public void setOperator(OperatorToken operator) {
        this.operator = operator;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(operator);
        expr.toTokens(writer);
    }
}
