package dev.v22.jmacros;

import java.nio.file.Path;

public class Span {
    private final Path sourceFile;
    private final int startCharOffset;
    private final int endCharOffset;

    public Span(Path sourceFile, int startCharOffset, int endCharOffset) {
        this.sourceFile = sourceFile;
        this.startCharOffset = startCharOffset;
        this.endCharOffset = endCharOffset;
    }

    public Path getSourceFile() {
        return sourceFile;
    }

    public int getStartCharOffset() {
        return startCharOffset;
    }

    public int getEndCharOffset() {
        return endCharOffset;
    }

    @Override
    public String toString() {
        return "Span{" +
                "sourceFile=" + sourceFile +
                ", startCharOffset=" + startCharOffset +
                ", endCharOffset=" + endCharOffset +
                '}';
    }
}
