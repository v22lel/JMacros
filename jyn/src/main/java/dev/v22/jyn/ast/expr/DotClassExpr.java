package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.KeywordToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.type.Type;

public class DotClassExpr extends Expression {
    private Type type;

    @Override
    public void toTokens(TokenStream writer) {
        type.toTokens(writer);
        writer.pushSingle(new Token.Dot());
        writer.pushSingle(new KeywordToken.Class());
    }
}
