package dev.v22.jmacros;

import java.nio.file.Path;

public class Span {
    private final Path sourceFile;
    private final int startCharOffset;
    private final int endCharOffset;
    private final int startColumn;
    private final int startRow;
    private final int endColumn;
    private final int endRow;

    public Span(Path sourceFile, int startCharOffset, int endCharOffset, int startColumn, int startRow, int endColumn, int endRow) {
        this.sourceFile = sourceFile;
        this.startCharOffset = startCharOffset;
        this.endCharOffset = endCharOffset;
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.endColumn = endColumn;
        this.endRow = endRow;
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

    public int getStartColumn() {
        return startColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public int getEndRow() {
        return endRow;
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
