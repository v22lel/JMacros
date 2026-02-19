package dev.v22.jmacros;

import com.carrotsearch.hppc.IntStack;
import dev.v22.jmacros.tokens.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TokenStream implements ToTokens, Iterable<Token> {
    private final List<Token> tokens;
    private int index;
    private IntStack markers;

    public TokenStream() {
        tokens = new ArrayList<>();
        markers = new IntStack();
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

    public Token consume() {
        return tokens.get(index++);
    }

    public Token peek() {
        return peek(0);
    }

    public Token peek(int dist) {
        return tokens.get(dist + index);
    }

    public boolean available() {
        return index < tokens.size();
    }

    public void mark() {
        markers.push(index);
    }

    public void unmark() {
        markers.pop();
    }

    public void reset() {
        index = markers.pop();
    }
}