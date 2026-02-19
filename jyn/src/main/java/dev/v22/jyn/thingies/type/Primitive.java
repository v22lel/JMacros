package dev.v22.jyn.thingies.type;

import dev.v22.jmacros.ToTokens;
import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.PrimitiveKeywordToken;
import dev.v22.jmacros.tokens.Token;

public enum Primitive implements ToTokens {
    Byte,
    Short,
    Int,
    Long,
    Float,
    Double,
    Boolean,
    Char;

    @Override
    public void toTokens(TokenStream writer) {
        Token tkn = switch (this) {
            case Byte -> new PrimitiveKeywordToken.Byte();
            case Short -> new PrimitiveKeywordToken.Short();
            case Int -> new PrimitiveKeywordToken.Int();
            case Long -> new PrimitiveKeywordToken.Long();
            case Float -> new PrimitiveKeywordToken.Float();
            case Double -> new PrimitiveKeywordToken.Double();
            case Boolean -> new PrimitiveKeywordToken.Boolean();
            case Char -> new PrimitiveKeywordToken.Char();
        };
        writer.pushSingle(tkn);
    }
}
