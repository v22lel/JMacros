package dev.v22.jyn.thingies.type;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.PrimitiveKeywordToken;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;

public class PrimitiveType extends Type {
    private Primitive primitive;

    public PrimitiveType(Primitive primitive) {
        this.primitive = primitive;
    }

    public Primitive getPrimitive() {
        return primitive;
    }

    public void setPrimitive(Primitive primitive) {
        this.primitive = primitive;
    }

    @Override
    public void toTokens(TokenStream writer) {
        primitive.toTokens(writer);
    }

    public static PrimitiveType parse(TokenStream tokens) throws AstException {
        PrimitiveKeywordToken pkw = AstNode.expect(tokens, PrimitiveKeywordToken.class);
        switch (pkw) {
            case PrimitiveKeywordToken.Byte _ -> {
                return new PrimitiveType(Primitive.Byte);
            }
            case PrimitiveKeywordToken.Short _ -> {
                return new PrimitiveType(Primitive.Short);
            }
            case PrimitiveKeywordToken.Int _ -> {
                return new PrimitiveType(Primitive.Int);
            }
            case PrimitiveKeywordToken.Long _ -> {
                return new PrimitiveType(Primitive.Long);
            }
            case PrimitiveKeywordToken.Float _ -> {
                return new PrimitiveType(Primitive.Float);
            }
            case PrimitiveKeywordToken.Double _ -> {
                return new PrimitiveType(Primitive.Double);
            }
            case PrimitiveKeywordToken.Char _ -> {
                return new PrimitiveType(Primitive.Char);
            }
            case PrimitiveKeywordToken.Boolean _ -> {
                return new PrimitiveType(Primitive.Boolean);
            }
            default -> throw new RuntimeException("what kind of tokens did u give me tf");
        }
    }
}
