package dev.v22.jyn.thingies.type;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;
import dev.v22.jyn.thingies.collections.generic.GenericConstraints;

public class WildcardType extends Type {
    private GenericBound bound;
    private GenericConstraints constraints;

    public WildcardType(GenericBound bound, GenericConstraints constraints) {
        this.bound = bound;
        this.constraints = constraints;
    }

    public GenericBound getBound() {
        return bound;
    }

    public void setBound(GenericBound bound) {
        this.bound = bound;
    }

    public GenericConstraints getConstraints() {
        return constraints;
    }

    public void setConstraints(GenericConstraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public void toTokens(TokenStream writer) {
        bound.toTokens(writer);
        constraints.toTokens(writer);
    }

    public static WildcardType parse(TokenStream tokens) throws AstException {
        return AstNode.wrapParse(tokens, ts -> {
            AstNode.expect(tokens, Token.QuestionMark.class);
            GenericBound bound = GenericBound.parse(ts);
            GenericConstraints constraints = GenericConstraints.parse(ts);
            return new WildcardType(bound, constraints);
        });
    }
}
