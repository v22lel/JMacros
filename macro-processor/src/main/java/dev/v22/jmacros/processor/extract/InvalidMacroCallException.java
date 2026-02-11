package dev.v22.jmacros.processor.extract;

import dev.v22.jmacros.tokens.Token;

public class InvalidMacroCallException extends Exception {
    public InvalidMacroCallException(Token at, String message) {
        super(String.format("\nSource: %s (character %d), at: %s", at.getSourceFile(), at.getStartCharOffset(), at.repr()));
    }

    public InvalidMacroCallException(Throwable cause) {
        super(cause);
    }

    public static InvalidMacroCallException invalidBlockType(Token found) {
        return new InvalidMacroCallException(found, "Invalid macro call! Found macro extry token " + found.repr() + ", expected either ( or [ or {");
    }

    public static InvalidMacroCallException partial(Token at) {
        return new InvalidMacroCallException(at, "Partial Macro call!");
    }
}
