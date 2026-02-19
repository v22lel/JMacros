package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.type.Type;

public class CastExpr extends Expression {
    private Type target;
    private Expression expr;

    public CastExpr(Type target, Expression expr) {
        this.target = target;
        this.expr = expr;
    }

    public Type getTarget() {
        return target;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(new Token.LeftParen());
        target.toTokens(writer);
        writer.pushSingle(new Token.RightParen());
        expr.toTokens(writer);
    }
}
