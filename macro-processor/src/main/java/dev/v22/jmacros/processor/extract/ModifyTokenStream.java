package dev.v22.jmacros.processor.extract;

import dev.v22.jmacros.TokenStream;

public class ModifyTokenStream extends TokenStream {
    public ModifyTokenStream(TokenStream base) {
        this.push(base);
    }

    public void insert(int pos, TokenStream insertion) {
        tokens.addAll(pos, insertion.tokens);
    }

    public void replace(int pos, int length, TokenStream replacement) {
        tokens.subList(pos, pos + length).clear();
        tokens.addAll(pos, replacement.tokens);
    }
}