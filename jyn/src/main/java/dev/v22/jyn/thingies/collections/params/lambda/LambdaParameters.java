package dev.v22.jyn.thingies.collections.params.lambda;

import dev.v22.jyn.ast.AstNode;

public abstract class LambdaParameters extends AstNode {
    public abstract int count();

    public boolean needsParens() {
        return this instanceof LambdaExplicitParameters || count() > 1 || count() == 0;
    }
}
