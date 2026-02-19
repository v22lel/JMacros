package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.OperatorToken;

//a++ or a--
public class PostfixExpr extends Expression {
    private Expression expr;
    private OperatorToken operator;

    public PostfixExpr(Expression expr, OperatorToken operator) {
        this.expr = expr;
        this.operator = operator;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public OperatorToken getOperator() {
        return operator;
    }

    public void setOperator(OperatorToken operator) {
        this.operator = operator;
    }

    @Override
    public void toTokens(TokenStream writer) {
        expr.toTokens(writer);
        writer.pushSingle(operator);
    }
}
