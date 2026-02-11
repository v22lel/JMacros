package dev.v22.jmacros.processor.extract;

import dev.v22.jmacros.Span;
import dev.v22.jmacros.TokenStream;

public record MacroCall (
    Span span,
    PathName macroName,
    TokenStream input,
    InputType inputType,
    int tokenPos,
    int tokenLength
) {
    @Override
    public String toString() {
        return "MacroCall{" +
                "span=" + span +
                ", macroName=" + macroName +
                ", input=" + input +
                ", inputType=" + inputType +
                ", tokenPos=" + tokenPos +
                ", tokenLength=" + tokenLength +
                '}';
    }
}
