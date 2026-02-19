package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.Ident;
import dev.v22.jyn.thingies.collections.Arguments;
import dev.v22.jyn.thingies.collections.generic.GenericsResolved;
import org.eclipse.sisu.Nullable;

public class MethodCallExpr extends Expression {
    private @Nullable GenericsResolved genericsResolved;
    private Ident name;
    private Arguments arguments;

    public MethodCallExpr(GenericsResolved genericsResolved, Ident name, Arguments arguments) {
        this.genericsResolved = genericsResolved;
        this.name = name;
        this.arguments = arguments;
    }

    public GenericsResolved getGenericsResolved() {
        return genericsResolved;
    }

    public void setGenericsResolved(GenericsResolved genericsResolved) {
        this.genericsResolved = genericsResolved;
    }

    public Ident getName() {
        return name;
    }

    public void setName(Ident name) {
        this.name = name;
    }

    public Arguments getArguments() {
        return arguments;
    }

    public void setArguments(Arguments arguments) {
        this.arguments = arguments;
    }

    @Override
    public void toTokens(TokenStream writer) {
        if (genericsResolved != null) {
            genericsResolved.toTokens(writer);
        }
        name.toTokens(writer);
        writer.pushSingle(new Token.LeftParen());
        arguments.toTokens(writer);
        writer.pushSingle(new Token.RightParen());
    }
}
