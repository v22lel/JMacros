package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.collections.params.lambda.LambdaParameters;
import dev.v22.jyn.thingies.collections.params.lambda.LambdaBody;

public class LambdaExpr extends Expression {
    private LambdaParameters lambdaParameters;
    private LambdaBody body;

    public LambdaExpr(LambdaParameters lambdaParameters, LambdaBody body) {
        this.lambdaParameters = lambdaParameters;
        this.body = body;
    }

    public LambdaParameters getLambdaParameters() {
        return lambdaParameters;
    }

    public void setLambdaParameters(LambdaParameters lambdaParameters) {
        this.lambdaParameters = lambdaParameters;
    }

    public LambdaBody getBody() {
        return body;
    }

    public void setBody(LambdaBody body) {
        this.body = body;
    }

    @Override
    public void toTokens(TokenStream writer) {
        boolean parens = lambdaParameters.needsParens();
        if (parens) {
            writer.pushSingle(new Token.LeftParen());
        }
        lambdaParameters.toTokens(writer);
        if (parens) {
            writer.pushSingle(new Token.RightParen());
        }
        writer.pushSingle(new Token.Arrow());
        body.toTokens(writer);
    }
}
