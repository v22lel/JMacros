package dev.v22.jmacros.tokens;

public class ErrorToken extends Token {
    private Throwable t;

    public ErrorToken(Throwable t) {
        this.t = t;
    }

    @Override
    public String repr() {
        return "Error: " + t;
    }
}
