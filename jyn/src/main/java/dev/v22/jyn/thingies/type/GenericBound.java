package dev.v22.jyn.thingies.type;

import dev.v22.jmacros.ToTokens;
import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.KeywordToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;

public enum GenericBound implements ToTokens {
    Extends,
    Super;

    @Override
    public void toTokens(TokenStream writer) {
        switch (this) {
            case Extends -> writer.pushSingle(new KeywordToken.Extends());
            case Super -> writer.pushSingle(new KeywordToken.Super());
        }
    }

    public static GenericBound parse(TokenStream tokens) throws AstException {
        return AstNode.wrapParse(tokens, ts -> {
            Token kw = ts.consume();
            if (kw instanceof KeywordToken.Extends) {
                return GenericBound.Extends;
            } else if (kw instanceof KeywordToken.Super) {
                return GenericBound.Super;
            }
            throw AstException.unexpected(kw);
        });
    }
}
