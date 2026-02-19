package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.KeywordToken;
import dev.v22.jyn.thingies.Ident;
import dev.v22.jyn.thingies.type.Type;
import org.eclipse.sisu.Nullable;

public class InstanceofExpr extends Expression {
    private Expression expr;
    private Type target;
    private @Nullable Ident pattern;

    public InstanceofExpr(Expression expr, Type target, @Nullable Ident pattern) {
        this.expr = expr;
        this.target = target;
        this.pattern = pattern;
    }

    public Expression getExpr() {
        return expr;
    }

    public Type getTarget() {
        return target;
    }

    public Ident getPattern() {
        return pattern;
    }

    @Override
    public void toTokens(TokenStream writer) {
        expr.toTokens(writer);
        writer.pushSingle(new KeywordToken.Instanceof());
        target.toTokens(writer);
        if (pattern != null) {
            pattern.toTokens(writer);
        }
    }
}
