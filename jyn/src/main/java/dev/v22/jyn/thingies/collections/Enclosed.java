package dev.v22.jyn.thingies.collections;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;
import dev.v22.jyn.thingies.Parser;

public class Enclosed<START extends Token, T extends AstNode, END extends Token> extends AstNode {
    private final START start;
    private T content;
    private final END end;

    public Enclosed(START start, T content, END end) {
        this.start = start;
        this.content = content;
        this.end = end;
    }

    public Enclosed(START start, END end) {
        this.start = start;
        this.end = end;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(start);
        if (content != null) {
            content.toTokens(writer);
        }
        writer.pushSingle(end);
    }

    public static <START extends Token, T extends AstNode, END extends Token> Enclosed<START, T, END> parse(TokenStream tokens, START start, END end, Parser<T> parser) throws AstException {
        if (!tokens.available()) throw AstException.noMoreTokens();
        Token s = tokens.consume();
        if (s.getClass() != start.getClass()) {
            throw AstException.unexpected(s);
        }
        T t = parser.parse(tokens);

        Token e = tokens.consume();
        if (e.getClass() != start.getClass()) {
            throw AstException.unexpected(s);
        }

        return new Enclosed<>(start, t, end);
    }
}
