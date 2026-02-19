package dev.v22.jyn.thingies.collections.params.lambda;

import dev.v22.jmacros.TokenStream;
import dev.v22.jyn.ast.stmt.Statement;

public class LambdaOneLinerBody extends LambdaBody {
    private Statement statement;

    public LambdaOneLinerBody(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void toTokens(TokenStream writer) {
        statement.toTokens(writer);
    }
}
