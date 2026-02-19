package dev.v22.jyn.thingies.collections.generic;

import dev.v22.jmacros.tokens.LogicalOperatorToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.collections.Enclosed;
import dev.v22.jyn.thingies.collections.Punctuated;

import java.util.List;

public class GenericList extends Enclosed<LogicalOperatorToken.Less, Punctuated<Generic, Token.Comma>, LogicalOperatorToken.Greater> {
    public GenericList() {
        super(new LogicalOperatorToken.Less(false), new LogicalOperatorToken.Greater(false));
    }

    public GenericList(List<Generic> generics) {
        super(new LogicalOperatorToken.Less(false), new Punctuated<>(generics, new Token.Comma()), new LogicalOperatorToken.Greater(false));
    }
}
