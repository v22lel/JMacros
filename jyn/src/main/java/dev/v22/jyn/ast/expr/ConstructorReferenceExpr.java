package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.KeywordToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.type.Type;

public class ConstructorReferenceExpr extends Expression {
    private Type type;

    public ConstructorReferenceExpr(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void toTokens(TokenStream writer) {
        type.toTokens(writer);
        writer.pushSingle(new Token.ColonColon());
        writer.pushSingle(new KeywordToken.New());
    }
}
