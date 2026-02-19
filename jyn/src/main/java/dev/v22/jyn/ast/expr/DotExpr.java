package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;

public class DotExpr extends Expression {
    private Expression parent;
    private Expression inner;

    public DotExpr(Expression parent, Expression inner) {
        this.parent = parent;
        this.inner = inner;
    }

    public Expression getParent() {
        return parent;
    }

    public void setParent(Expression parent) {
        this.parent = parent;
    }

    public Expression getInner() {
        return inner;
    }

    public void setInner(Expression inner) {
        this.inner = inner;
    }

    @Override
    public void toTokens(TokenStream writer) {
        parent.toTokens(writer);
        writer.pushSingle(new Token.Dot());
        inner.toTokens(writer);
    }
}
