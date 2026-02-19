package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;

//just so the source stays intact
public class ParenthesizedExpr extends Expression {
    private Expression expr;

    public ParenthesizedExpr(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(new Token.LeftParen());
        expr.toTokens(writer);
        writer.pushSingle(new Token.RightParen());
    }
}
