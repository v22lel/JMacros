package dev.v22.jmacros.tokens;

public abstract class OperatorToken extends Token {
    protected boolean eq;

    public OperatorToken(boolean eq) {
        this.eq = eq;
    }

    protected abstract String baseRepr();

    public boolean isEq() {
        return eq;
    }

    @Override
    public String repr() {
        return baseRepr() + (eq ? "=" : "");
    }
    
    public static class Plus extends OperatorToken { @Override protected String baseRepr() { return "+"; } public Plus(boolean eq) { super(eq); } }
    public static class PlusPlus extends OperatorToken { @Override protected String baseRepr() { return "+"; } public PlusPlus() { super(false); } }
    public static class Minus extends OperatorToken { @Override protected String baseRepr() { return "-"; } public Minus(boolean eq) { super(eq); } }
    public static class MinusMinus extends OperatorToken { @Override protected String baseRepr() { return "-"; } public MinusMinus() { super(false); } }
    public static class Mul extends OperatorToken { @Override protected String baseRepr() { return "*"; } public Mul(boolean eq) { super(eq); } }
    public static class Div extends OperatorToken { @Override protected String baseRepr() { return "/"; } public Div(boolean eq) { super(eq); } }
    public static class Mod extends OperatorToken { @Override protected String baseRepr() { return "%"; } public Mod(boolean eq) { super(eq); } }
    public static class Lsh extends OperatorToken { @Override protected String baseRepr() { return "<<"; } public Lsh(boolean eq) { super(eq); } }
    public static class Rsh extends OperatorToken { @Override protected String baseRepr() { return ">>"; } public Rsh(boolean eq) { super(eq); } }
    public static class UnsignedRsh extends OperatorToken { @Override protected String baseRepr() { return ">>>"; } public UnsignedRsh(boolean eq) { super(eq); } }
    public static class Or extends OperatorToken { @Override protected String baseRepr() { return "|"; } public Or(boolean eq) { super(eq); } }
    public static class And extends OperatorToken { @Override protected String baseRepr() { return "&"; } public And(boolean eq) { super(eq); } }
    public static class Xor extends OperatorToken { @Override protected String baseRepr() { return "^"; } public Xor(boolean eq) { super(eq); } }
}
