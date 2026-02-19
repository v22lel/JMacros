package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.Ident;
import dev.v22.jyn.thingies.type.Type;

public class MethodReferenceExpr extends Expression {
    private Type type;
    private Ident method;

    public MethodReferenceExpr(Type type, Ident method) {
        this.type = type;
        this.method = method;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Ident getMethod() {
        return method;
    }

    public void setMethod(Ident method) {
        this.method = method;
    }

    @Override
    public void toTokens(TokenStream writer) {
        type.toTokens(writer);
        writer.pushSingle(new Token.ColonColon());
        method.toTokens(writer);
    }
}
