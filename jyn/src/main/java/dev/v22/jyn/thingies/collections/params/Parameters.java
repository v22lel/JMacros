package dev.v22.jyn.thingies.collections.params;

import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.collections.Punctuated;

import java.util.List;

public class Parameters extends Punctuated<Parameter, Token.Comma> {
    public Parameters(List<Parameter> segments) {
        super(segments, new Token.Comma());
    }

    public Parameters() {
        this(List.of());
    }
}
