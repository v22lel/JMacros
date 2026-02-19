package dev.v22.jyn.thingies.collections.generic;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.OperatorToken;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.thingies.type.Type;
import dev.v22.jyn.thingies.collections.Punctuated;

import java.util.List;

public class GenericConstraints extends Punctuated<Type, OperatorToken.And> {
    public GenericConstraints(List<Type> superTypes) {
        super(superTypes, new OperatorToken.And(false));
    }

    public static GenericConstraints parse(TokenStream tokens) throws AstException {
        return (GenericConstraints) Punctuated.parse(
                tokens,
                new OperatorToken.And(false),
                false,
                Type::parse
        );
    }
}
