package dev.v22.jyn.thingies.collections;

import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.expr.Expression;

import java.util.Collections;
import java.util.List;

public class Arguments extends Punctuated<Expression, Token.Comma> {
    public Arguments() {
        super(Collections.emptyList(), new Token.Comma());
    }

    public Arguments(List<Expression> arguments) {
        super(arguments, new Token.Comma());
    }
}
