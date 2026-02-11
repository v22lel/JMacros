package dev.v22.jmacros.tokens;

import dev.v22.jmacros.ToTokens;
import dev.v22.jmacros.TokenStream;

import java.nio.file.Path;

public abstract class Token implements ToTokens {
    protected Path sourceFile;
    protected int startCharOffset;
    protected int endCharOffset;

    public abstract String repr();

    public Path getSourceFile() {
        return sourceFile;
    }

    public int getStartCharOffset() {
        return startCharOffset;
    }

    public int getEndCharOffset() {
        return endCharOffset;
    }

    public void setSourceFile(Path sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void setStartCharOffset(int startCharOffset) {
        this.startCharOffset = startCharOffset;
    }

    public void setEndCharOffset(int endCharOffset) {
        this.endCharOffset = endCharOffset;
    }

    @Override
    public void toTokens(TokenStream writer) {
        writer.pushSingle(this);
    }

    public static class Dot extends Token { @Override public String repr() { return "."; } }
    public static class Colon extends Token { @Override public String repr() { return ":"; } }
    public static class Comma extends Token { @Override public String repr() { return ","; } }
    public static class Semicolon extends Token { @Override public String repr() { return ";"; } }
    public static class ColonColon extends Token { @Override public String repr() { return "::"; } }
    public static class ExclamationMark extends Token { @Override public String repr() { return "!"; } }
    public static class QuestionMark extends Token { @Override public String repr() { return "?"; } }
    public static class LeftParen extends Token { @Override public String repr() { return "("; } }
    public static class RightParen extends Token { @Override public String repr() { return ")"; } }
    public static class LeftBracket extends Token { @Override public String repr() { return "["; } }
    public static class RightBracket extends Token { @Override public String repr() { return "]"; } }
    public static class LeftBrace extends Token { @Override public String repr() { return "{"; } }
    public static class RightBrace extends Token { @Override public String repr() { return "}"; } }
    public static class Eq extends Token { @Override public String repr() { return "="; } }
    public static class At extends Token { @Override public String repr() { return "@"; } }
}
