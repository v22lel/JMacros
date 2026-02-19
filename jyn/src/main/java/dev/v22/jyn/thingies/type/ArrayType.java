package dev.v22.jyn.thingies.type;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.Token;

public class ArrayType extends Type {
    private Type componentType;
    private int depth;

    public ArrayType(Type componentType) {
        this.componentType = componentType;
    }

    public ArrayType(Type componentType, int depth) {
        this.componentType = componentType;
        this.depth = depth;
    }

    public Type getComponentType() {
        return componentType;
    }

    public void setComponentType(Type componentType) {
        this.componentType = componentType;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public void toTokens(TokenStream writer) {
        componentType.toTokens(writer);
        for (int i = 0; i < depth; i++) {
            writer.pushSingle(new Token.LeftBracket());
            writer.pushSingle(new Token.RightBracket());
        }
    }
}
