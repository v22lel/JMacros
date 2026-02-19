package dev.v22.jyn.ast;

import dev.v22.jmacros.tokens.Token;

public class AstException extends Exception {
    public AstException(String message) {
        super(message);
    }

    public AstException(Throwable cause) {
        super(cause);
    }

    public static AstException noMoreTokens() {
        return new AstException("No more Tokens available for parsing!");
    }

    public static AstException unexpected(Token token) {
        return new AstException("Unexpected Token: " + token.repr());
    }
}
