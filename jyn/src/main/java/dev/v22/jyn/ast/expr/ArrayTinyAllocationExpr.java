package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jyn.thingies.collections.ArrayInitializer;

public class ArrayTinyAllocationExpr extends Expression {
    private ArrayInitializer initializer;

    public ArrayTinyAllocationExpr(ArrayInitializer initializer) {
        this.initializer = initializer;
    }

    public ArrayInitializer getInitializer() {
        return initializer;
    }

    public void setInitializer(ArrayInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public void toTokens(TokenStream writer) {
        initializer.toTokens(writer);
    }
}
