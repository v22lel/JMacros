package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.LiteralToken;

public class LiteralExpr extends Expression {
    private LiteralToken token;

    public LiteralExpr(LiteralToken token) {
        this.token = token;
    }

    public LiteralToken getToken() {
        return token;
    }

    public void setToken(LiteralToken token) {
        this.token = token;
    }

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(token);
    }
}
