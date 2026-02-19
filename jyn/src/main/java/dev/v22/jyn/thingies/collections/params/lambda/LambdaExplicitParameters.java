package dev.v22.jyn.thingies.collections.params.lambda;

import dev.v22.jmacros.TokenStream;
import dev.v22.jyn.thingies.collections.params.Parameters;

public class LambdaExplicitParameters extends LambdaParameters {
    private Parameters parameters;

    public LambdaExplicitParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public void toTokens(TokenStream writer) {
        parameters.toTokens(writer);
    }

    @Override
    public int count() {
        return parameters.getSegments().size();
    }
}
