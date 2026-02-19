package dev.v22.jyn.thingies;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.IdentToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;

public class Ident extends AstNode {
    private final String ident;

    public Ident(String ident) {
        this.ident = ident;
    }

    public String getIdent() {
        return ident;
    }

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(new IdentToken(ident));
    }

    public static Ident parse(TokenStream tokens) throws AstException {
        if (!tokens.available()) throw AstException.noMoreTokens();
        tokens.mark();
        Token tkn = tokens.consume();
        tokens.unmark();
        if (tkn instanceof IdentToken iTkn) {
            return new Ident(iTkn.getIdent());
        } else {
            tokens.reset();
            throw AstException.unexpected(tkn);
        }
    }
}
