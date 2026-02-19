package dev.v22.jyn.thingies.collections;

import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.expr.Expression;

import java.util.List;

public class ArrayInitializer extends Punctuated<Expression, Token.Comma> {
    public ArrayInitializer(List<Expression> segments) {
        super(segments, new Token.Comma(), true);
    }

    public ArrayInitializer() {
        this(List.of());
    }
}
