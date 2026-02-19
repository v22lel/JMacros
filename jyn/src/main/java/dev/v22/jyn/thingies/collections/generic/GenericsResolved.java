package dev.v22.jyn.thingies.collections.generic;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.LogicalOperatorToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.thingies.collections.Enclosed;
import dev.v22.jyn.thingies.collections.Punctuated;
import dev.v22.jyn.thingies.type.Type;

import java.util.List;

public class GenericsResolved extends Enclosed<LogicalOperatorToken.Less, Punctuated<Type, Token.Comma>, LogicalOperatorToken.Greater> {
    public GenericsResolved() {
        super(new LogicalOperatorToken.Less(false), new LogicalOperatorToken.Greater(false));
    }

    public GenericsResolved(List<Type> resolvedTypes) {
        super(new LogicalOperatorToken.Less(false), new Punctuated<>(resolvedTypes, new Token.Comma()), new LogicalOperatorToken.Greater(false));
    }

    public static GenericsResolved parse(TokenStream tokens) throws AstException {
        return (GenericsResolved) GenericsResolved.parse(
                tokens,
                new LogicalOperatorToken.Less(false),
                new LogicalOperatorToken.Greater(false),
                t -> Punctuated.parse(t, new Token.Comma(), false, Type::parse)
        );
    }
}
