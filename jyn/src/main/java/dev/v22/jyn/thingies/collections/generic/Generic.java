package dev.v22.jyn.thingies.collections.generic;

import dev.v22.jmacros.TokenStream;
import dev.v22.jyn.ast.AstNode;
import dev.v22.jyn.thingies.Ident;
import dev.v22.jyn.thingies.type.GenericBound;
import dev.v22.jyn.thingies.type.Type;
import org.eclipse.sisu.Nullable;

import java.util.List;

public class Generic extends AstNode {
    private Ident name;
    private @Nullable GenericBound bound;
    private @Nullable GenericConstraints constraints;

    public Generic(Ident name, @Nullable GenericBound bound, @Nullable GenericConstraints constraints) {
        this.name = name;
        this.bound = bound;
        this.constraints = constraints;
    }

    public Generic(Ident name) {
        this.name = name;
    }

    public Ident getName() {
        return name;
    }

    public void setName(Ident name) {
        this.name = name;
    }

    public GenericBound getBound() {
        return bound;
    }

    public GenericConstraints getConstraints() {
        return constraints;
    }

    public void removeConstraints() {
        this.bound = null;
        this.constraints = null;
    }

    public void setConstraints(GenericBound bound, List<Type> constraints) {
        this.bound = bound;
        this.constraints = new GenericConstraints(constraints);
    }

    @Override
    public void toTokens(TokenStream writer) {
        name.toTokens(writer);
        if (bound != null) {
            bound.toTokens(writer);
            constraints.toTokens(writer);
        }
    }
}
