package dev.v22.jyn.thingies;

import dev.v22.jmacros.TokenStream;
import dev.v22.jyn.ast.AstException;
import dev.v22.jyn.ast.AstNode;

@FunctionalInterface
public interface Parser<T> {
    T parse(TokenStream tokens) throws AstException;
}
