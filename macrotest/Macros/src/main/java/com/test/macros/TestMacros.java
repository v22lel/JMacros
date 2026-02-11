package com.test.macros;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.macro.Macro;
import dev.v22.jmacros.tokens.IdentToken;
import dev.v22.jmacros.tokens.LiteralToken;
import dev.v22.jmacros.tokens.OperatorToken;
import dev.v22.jmacros.tokens.Token;

public class TestMacros {
    @Macro
    public static TokenStream helloWorld(TokenStream input) {
        TokenStream ts = new TokenStream();
        ts.push(new IdentToken("System"));
        ts.push(new Token.Dot());
        ts.push(new IdentToken("out"));
        ts.push(new Token.Dot());
        ts.push(new IdentToken("println"));
        ts.push(new Token.LeftParen());
        ts.push(new LiteralToken.Str("Hello World!"));
        ts.push(new Token.RightParen());
        return ts;
    }

    @Macro
    public static TokenStream fmt(TokenStream input) {
        LiteralToken.Str lit = (LiteralToken.Str) input.iterator().next();
        String s = lit.getString();

        TokenStream out = new TokenStream();

        StringBuilder literal = new StringBuilder();
        StringBuilder field = new StringBuilder();

        boolean inField = false;
        boolean firstSegment = true;

        for (char c : s.toCharArray()) {
            if (c == '{') {
                if (inField) {
                    throw new IllegalArgumentException("Nested '{' in fmt()");
                }

                // flush literal
                if (!literal.isEmpty()) {
                    emitPlusIfNeeded(out, firstSegment);
                    out.push(new LiteralToken.Str(literal.toString()));
                    literal.setLength(0);
                    firstSegment = false;
                }

                inField = true;
                continue;
            }

            if (c == '}') {
                if (!inField) {
                    throw new IllegalArgumentException("Unmatched '}' in fmt()");
                }

                // flush field
                emitPlusIfNeeded(out, firstSegment);
                out.push(new IdentToken(field.toString()));
                field.setLength(0);
                firstSegment = false;

                inField = false;
                continue;
            }

            if (inField) {
                field.append(c);
            } else {
                literal.append(c);
            }
        }

        // flush tail
        if (inField) {
            throw new IllegalArgumentException("Unclosed '{' in fmt()");
        }

        if (!literal.isEmpty()) {
            emitPlusIfNeeded(out, firstSegment);
            out.push(new LiteralToken.Str(literal.toString()));
        }

        return out;
    }

    private static void emitPlusIfNeeded(TokenStream ts, boolean first) {
        if (!first) {
            ts.push(new OperatorToken.Plus(false));
        }
    }

}
