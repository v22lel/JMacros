package dev.v22.jmacros.tokens;

public class IdentToken extends Token {
    private String ident;

    public IdentToken(String ident) {
        this.ident = ident;
    }

    public String getIdent() {
        return ident;
    }

    @Override
    public String repr() {
        return ident;
    }
}
