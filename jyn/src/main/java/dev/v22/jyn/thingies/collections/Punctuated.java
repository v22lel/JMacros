package dev.v22.jyn.thingies.collections;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;
import dev.v22.jyn.thingies.Parser;

import java.util.ArrayList;
import java.util.List;

public class Punctuated<T extends AstNode, P extends Token> extends AstNode {
    private List<T> segments;
    private final P punctuation;
    private boolean trailing;

    public Punctuated(List<T> segments, P punctuation) {
        this.segments = segments;
        this.punctuation = punctuation;
        this.trailing = false;
    }

    public Punctuated(List<T> segments, P punctuation, boolean trailing) {
        this.segments = segments;
        this.punctuation = punctuation;
        this.trailing = trailing;
    }

    public List<T> getSegments() {
        return segments;
    }

    public P getPunctuation() {
        return punctuation;
    }

    public boolean isTrailing() {
        return trailing;
    }

    public void setTrailing(boolean trailing) {
        this.trailing = trailing;
    }

    public void setSegments(List<T> segments) {
        this.segments = segments;
    }

    @Override
    public void toTokens(TokenStream writer) {
        for (int i = 0; i < segments.size(); i++) {
            segments.get(i).toTokens(writer);
            if (i + 1 < segments.size() || trailing) {
                writer.pushSingle(punctuation);
            }
        }
    }

    public static <T extends AstNode, P extends Token> Punctuated<T, P> parse(TokenStream tokens, P punctuation, boolean allowTrailing, Parser<T> parser) throws AstException {
        boolean trailing = false;
        List<T> segments = new ArrayList<>();
        boolean first = true;
        while (true) {
            try {
                tokens.mark();
                T segment = parser.parse(tokens);
                tokens.unmark();
                segments.add(segment);
                Token next = tokens.peek();
                if (next.getClass() != punctuation.getClass()) {
                    break;
                }
                tokens.consume();
                first = false;
            } catch (AstException _) {
                tokens.reset();
                if (first == allowTrailing) {
                    throw new AstException("Found trailing " + punctuation.repr() + ", but that is strictly forbidden!");
                }
                if (!first) {
                    trailing = true;
                }
                break;
            }
        }

        return new Punctuated<>(segments, punctuation, trailing);
    }
}
