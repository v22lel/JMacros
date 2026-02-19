package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;

public class ArrayIndexExpr extends Expression {
    private Expression array;
    private Expression index;

    public ArrayIndexExpr(Expression array, Expression index) {
        this.array = array;
        this.index = index;
    }

    public Expression getArray() {
        return array;
    }

    public void setArray(Expression array) {
        this.array = array;
    }

    public Expression getIndex() {
        return index;
    }

    public void setIndex(Expression index) {
        this.index = index;
    }

    @Override
    public void toTokens(TokenStream writer) {
        array.toTokens(writer);
        writer.pushSingle(new Token.LeftBracket());
        index.toTokens(writer);
        writer.pushSingle(new Token.RightBracket());
    }
}
