package dev.v22.jyn.thingies.type;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.LogicalOperatorToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;
import dev.v22.jyn.thingies.Ident;
import dev.v22.jyn.thingies.collections.Punctuated;
import dev.v22.jyn.thingies.collections.generic.GenericsResolved;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClassType extends Type {
    private Punctuated<Ident, Token.Dot> segments;
    private @Nullable GenericsResolved generics;

    public ClassType(Punctuated<Ident, Token.Dot> segments, @Nullable GenericsResolved generics) {
        this.segments = segments;
        this.generics = generics;
    }

    public ClassType(List<Ident> segments, @Nullable GenericsResolved generics) {
        this.segments = new Punctuated<>(segments, new Token.Dot());
        this.generics = generics;
    }

    public ClassType(Ident oneIdent) {
        this(List.of(oneIdent), null);
    }

    public Ident getLast() {
        return getSegments().getLast();
    }

    public List<Ident> getSegments() {
        return segments.getSegments();
    }

    public void setSegments(Punctuated<Ident, Token.Dot> segments) {
        this.segments = segments;
    }

    public @Nullable GenericsResolved getGenerics() {
        return generics;
    }

    public void setGenerics(@Nullable GenericsResolved generics) {
        this.generics = generics;
    }

    @Override
    public void toTokens(TokenStream writer) {
        segments.toTokens(writer);
        if (generics != null) {
            generics.toTokens(writer);
        }
    }

    public static ClassType parse(TokenStream tokens) throws AstException {
        return AstNode.wrapParse(tokens, ts -> {
            Punctuated<Ident, Token.Dot> segments = Punctuated.parse(ts, new Token.Dot(), false, Ident::parse);

            GenericsResolved genericsResolved;
            if (ts.available() && ts.peek() instanceof LogicalOperatorToken.Less) {
                genericsResolved = GenericsResolved.parse(ts);
            } else {
                genericsResolved = null;
            }

            return new ClassType(segments, genericsResolved);
        });
    }
}
