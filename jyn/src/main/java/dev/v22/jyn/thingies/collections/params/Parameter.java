package dev.v22.jyn.thingies.collections.params;

import dev.v22.jmacros.TokenStream;
import dev.v22.jyn.ast.AstNode;
import dev.v22.jyn.thingies.Ident;
import dev.v22.jyn.thingies.type.Type;

public class Parameter extends AstNode {
    private Type type;
    private Ident name;

    public Parameter(Type type, Ident name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Ident getName() {
        return name;
    }

    public void setName(Ident name) {
        this.name = name;
    }

    @Override
    public void toTokens(TokenStream writer) {
        type.toTokens(writer);
        name.toTokens(writer);
    }
}
