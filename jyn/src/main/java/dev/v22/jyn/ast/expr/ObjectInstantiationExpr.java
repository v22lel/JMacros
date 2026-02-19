package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.KeywordToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.stmt.BlockStmt;
import dev.v22.jyn.thingies.type.Type;
import dev.v22.jyn.thingies.collections.Arguments;
import dev.v22.jyn.thingies.collections.generic.GenericsResolved;
import org.jetbrains.annotations.Nullable;

public class ObjectInstantiationExpr extends Expression {
    private Type target;
    private GenericsResolved genericsResolved;
    private Arguments arguments;
    private @Nullable BlockStmt anonymous;

    public ObjectInstantiationExpr(Type target, GenericsResolved genericsResolved, Arguments arguments, @Nullable BlockStmt anonymous) {
        this.target = target;
        this.genericsResolved = genericsResolved;
        this.arguments = arguments;
        this.anonymous = anonymous;
    }

    public ObjectInstantiationExpr(Type target, GenericsResolved genericsResolved) {
        this(target, genericsResolved, new Arguments(), null);
    }

    public ObjectInstantiationExpr(Type target) {
        this(target, null, new Arguments(), null);
    }

    public ObjectInstantiationExpr(Type target, GenericsResolved genericsResolved, Arguments arguments) {
        this(target, genericsResolved, arguments, null);
    }

    public ObjectInstantiationExpr(Type target, Arguments arguments) {
        this(target, null, arguments, null);
    }



    public ObjectInstantiationExpr(Type target, GenericsResolved genericsResolved, BlockStmt anonymous) {
        this(target, genericsResolved, new Arguments(), anonymous);
    }

    public ObjectInstantiationExpr(Type target, BlockStmt anonymous) {
        this(target, null, new Arguments(), anonymous);
    }

    public ObjectInstantiationExpr(Type target, Arguments arguments, BlockStmt anonymous) {
        this(target, null, arguments, anonymous);
    }

    public Type getTarget() {
        return target;
    }

    public GenericsResolved getGenericsResolved() {
        return genericsResolved;
    }

    public Arguments getArguments() {
        return arguments;
    }

    public BlockStmt getAnonymous() {
        return anonymous;
    }


    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(new KeywordToken.New());
        target.toTokens(writer);
        if (genericsResolved != null) {
            genericsResolved.toTokens(writer);
        }
        writer.pushSingle(new Token.LeftParen());
        arguments.toTokens(writer);
        writer.pushSingle(new Token.RightParen());
        if (anonymous != null) {
            anonymous.toTokens(writer);
        }
    }
}
