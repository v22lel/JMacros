package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.KeywordToken;

public class SuperExpr extends Expression {

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(new KeywordToken.Super());
    }
}
