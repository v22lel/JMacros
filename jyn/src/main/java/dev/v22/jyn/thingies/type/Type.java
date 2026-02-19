package dev.v22.jyn.thingies.type;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.IdentToken;
import dev.v22.jmacros.tokens.PrimitiveKeywordToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;

public abstract class Type extends AstNode {
    public static Type parse(TokenStream tokens) throws AstException {
        return AstNode.wrapParse(tokens, ts -> {
            Token next = ts.peek();
            Type base = switch (next) {
                case PrimitiveKeywordToken _ -> PrimitiveType.parse(ts);
                case IdentToken _ -> ClassType.parse(ts);
                case Token.QuestionMark _ -> WildcardType.parse(ts);

                default -> throw AstException.unexpected(next);
            };

            int arrayDepth = 0;
            while (ts.available()) {
                next = ts.peek();

                if (next instanceof Token.LeftBracket) {
                    arrayDepth++;
                    ts.consume();
                    AstNode.expect(ts, Token.RightBracket.class);
                } else {
                    break;
                }
            }

            return arrayDepth == 0 ? base : new ArrayType(base, arrayDepth);
        });
    }
}
