package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.*;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;

public abstract class Expression extends AstNode {
    public static Expression parse(TokenStream tokens) throws AstException {
        if (!tokens.available()) throw AstException.noMoreTokens();

        //fuck i hate my life lets go
        Token next = tokens.consume();
        switch (next) {
            case Token.LeftParen _ -> {
                //so this can either be a parentesized expression or a cast
                //to know which one, i have to check if the next expression is a unary one WITHOUT a + or - as operator

            }

            default -> throw new IllegalStateException("Unexpected value: " + next);
        }
    }

    private static boolean canStartExpression(Token token) {
        return switch (token) {
            case Token.LeftParen _,
                 IdentToken _,
                 KeywordToken.New _,
                 KeywordToken.This _,
                 KeywordToken.Super _,
                 OperatorToken _ , //unary
                 LiteralToken _ -> true;
            default -> false;
        };
    }
}
