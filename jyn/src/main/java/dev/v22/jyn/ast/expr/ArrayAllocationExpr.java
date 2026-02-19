package dev.v22.jyn.ast.expr;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.KeywordToken;
import dev.v22.jmacros.tokens.Token;
import dev.v22.jyn.thingies.collections.ArrayInitializer;
import dev.v22.jyn.thingies.type.Type;
import org.eclipse.sisu.Nullable;

import java.util.List;

public class ArrayAllocationExpr extends Expression {
    private Type componentType;
    private @Nullable List<Expression> dimensions;
    private int depth;
    private @Nullable ArrayInitializer initializer;

    public ArrayAllocationExpr(Type componentType, List<Expression> dimensions) {
        this.componentType = componentType;
        this.dimensions = dimensions;
        this.depth = dimensions.size();
        this.initializer = null;
    }

    public ArrayAllocationExpr(Type componentType, int depth, ArrayInitializer initializer) {
        this.componentType = componentType;
        this.dimensions = null;
        this.depth = depth;
        this.initializer = initializer;
    }

    public ArrayInitializer getInitializer() {
        return initializer;
    }

    public void setInitializer(ArrayInitializer initializer) {
        this.initializer = initializer;
    }

    public List<Expression> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Expression> dimensions) {
        this.dimensions = dimensions;
        this.depth = dimensions.size();
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
        writer.pushSingle(new KeywordToken.New());
        componentType.toTokens(writer);
        for (int i = 0; i < depth; i++) {
            writer.pushSingle(new Token.LeftBracket());
            if (dimensions != null) {
                dimensions.get(i).toTokens(writer);
            }
            writer.pushSingle(new Token.RightBracket());
        }
        if (initializer != null) {
            writer.pushSingle(new Token.LeftBrace());
            initializer.toTokens(writer);
            writer.pushSingle(new Token.RightBrace());
        }
    }
}
