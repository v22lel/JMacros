package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;

public class TernaryExpr extends Expression {
    private Expression cond;
    private Expression truePath;
    private Expression falsePath;

    public TernaryExpr(Expression cond, Expression truePath, Expression falsePath) {
        this.cond = cond;
        this.truePath = truePath;
        this.falsePath = falsePath;
    }

    public Expression getCond() {
        return cond;
    }

    public void setCond(Expression cond) {
        this.cond = cond;
    }

    public Expression getTruePath() {
        return truePath;
    }

    public void setTruePath(Expression truePath) {
        this.truePath = truePath;
    }

    public Expression getFalsePath() {
        return falsePath;
    }

    public void setFalsePath(Expression falsePath) {
        this.falsePath = falsePath;
    }

    @Override
    public void toTokens(TokenStream writer) {
        cond.toTokens(writer);
        writer.pushSingle(new Token.QuestionMark());
        truePath.toTokens(writer);
        writer.pushSingle(new Token.Colon());
        falsePath.toTokens(writer);
    }
}
