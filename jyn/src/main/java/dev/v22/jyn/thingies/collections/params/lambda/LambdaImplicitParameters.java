package dev.v22.jyn.thingies.collections.params.lambda;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.Ident;
import dev.v22.jyn.thingies.collections.Punctuated;

import java.util.List;

public class LambdaImplicitParameters extends LambdaParameters {
    private Punctuated<Ident, Token.Comma> list;

    public LambdaImplicitParameters(List<Ident> segments) {
        list = new Punctuated<>(segments, new Token.Comma());
    }

    public LambdaImplicitParameters() {
        this(List.of());
    }

    public Punctuated<Ident, Token.Comma> getList() {
        return list;
    }

    public void setList(Punctuated<Ident, Token.Comma> list) {
        this.list = list;
    }

    @Override
    public void toTokens(TokenStream writer) {
        list.toTokens(writer);
    }

    @Override
    public int count() {
        return list.getSegments().size();
    }
}
