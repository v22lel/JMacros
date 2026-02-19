package dev.v22.jyn.ast;

import dev.v22.jmacros.ToTokens;
import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.Parser;

public abstract class AstNode implements ToTokens {
    public static <T extends Token> T expect(TokenStream source, Class<T> cls) throws AstException {
        if (!source.available()) throw AstException.noMoreTokens();

        Token t = source.consume();
        if (!cls.isInstance(t)) {
            throw AstException.unexpected(t);
        }
        return cls.cast(t);
    }

    public static <T> T wrapParse(TokenStream tokens, Parser<T> parser) throws AstException {
        if (!tokens.available()) throw AstException.noMoreTokens();
        try {
            tokens.mark();
            T t = parser.parse(tokens);
            tokens.unmark();
            return t;
        } catch (AstException e) {
            tokens.reset();
            throw e;
        }
    }
}
