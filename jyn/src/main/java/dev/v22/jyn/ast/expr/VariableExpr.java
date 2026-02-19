package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jyn.thingies.Ident;

public class VariableExpr extends Expression {
    private Ident ident;

    public VariableExpr(Ident ident) {
        this.ident = ident;
    }

    public Ident getIdent() {
        return ident;
    }

    @Override
    public void toTokens(TokenStream writer) {
        ident.toTokens(writer);
    }
}
