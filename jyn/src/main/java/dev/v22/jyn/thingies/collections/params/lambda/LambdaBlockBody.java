package dev.v22.jyn.thingies.collections.params.lambda;

import dev.v22.jmacros.TokenStream;
import dev.v22.jyn.ast.stmt.BlockStmt;

public class LambdaBlockBody extends LambdaBody {
    private BlockStmt block;

    public LambdaBlockBody(BlockStmt block) {
        this.block = block;
    }

    public BlockStmt getBlock() {
        return block;
    }

    public void setBlock(BlockStmt block) {
        this.block = block;
    }

    @Override
    public void toTokens(TokenStream writer) {
        block.toTokens(writer);
    }
}
