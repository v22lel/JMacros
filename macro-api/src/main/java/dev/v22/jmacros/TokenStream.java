package dev.v22.jmacros;

import dev.v22.jmacros.tokens.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TokenStream implements ToTokens, Iterable<Token> {
    public final List<Token> tokens;

    public TokenStream() {
        tokens = new ArrayList<>();
    }

    public void push(ToTokens tokens) {
        tokens.toTokens(this);
    }

    public void pushSingle(Token token) {
        tokens.add(token);
    }

    @Override
    public void toTokens(TokenStream writer) {
        this.tokens.forEach(writer::pushSingle);
    }

    public int len() {
        return tokens.size();
    }

    @Override
    public String toString() {
        return tokens.toString();
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    public static TokenStream ofTokens(Iterable<Token> iter) {
        TokenStream s = new TokenStream();
        iter.forEach(s::pushSingle);
        return s;
    }
}