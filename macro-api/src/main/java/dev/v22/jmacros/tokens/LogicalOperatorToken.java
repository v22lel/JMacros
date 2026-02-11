package dev.v22.jmacros.tokens;

public abstract class LogicalOperatorToken extends OperatorToken {
    public LogicalOperatorToken(boolean eq) {
        super(eq);
    }

    public static class Less extends LogicalOperatorToken { @Override protected String baseRepr() { return "<"; } public Less(boolean eq) { super(eq); } }
    public static class Greater extends LogicalOperatorToken { @Override protected String baseRepr() { return ">"; } public Greater(boolean eq) { super(eq); } }
    public static class Or extends LogicalOperatorToken { @Override protected String baseRepr() { return "||"; } public Or() { super(false); } }
    public static class And extends LogicalOperatorToken { @Override protected String baseRepr() { return "&&"; } public And() { super(false); } }
    public static class EqEq extends LogicalOperatorToken { @Override protected String baseRepr() { return "&&"; } public EqEq() { super(false); } }
}
