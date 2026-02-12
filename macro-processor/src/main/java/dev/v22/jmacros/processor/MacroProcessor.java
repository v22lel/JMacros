package dev.v22.jmacros.processor;

import dev.v22.jmacros.Span;
import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.processor.extract.MacroCall;
import dev.v22.jmacros.processor.extract.MacroCallFinder;
import dev.v22.jmacros.processor.extract.PathName;
import dev.v22.jmacros.tokens.Token;
import org.eclipse.sisu.space.Tokens;

import javax.naming.NameNotFoundException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MacroProcessor {
    private static final String MACRO_IMPORT_NAME = "importmacro";

    private final MacroCallFinder callFinder;
    private final MacroCollector collector;
    private List<Replacement> replacements;
    private StringBuilder fileBuilder;

    public MacroProcessor(MacroCallFinder callFinder, MacroCollector collector) {
        this.callFinder = callFinder;
        this.collector = collector;
    }

    public String processWholeFile(Path file) throws Exception {
        this.fileBuilder = new StringBuilder(Files.readString(file));
        this.replacements = new ArrayList<>();

        //first step: handle inputs

        HashMap<String, PathName> imports = new HashMap<>();
        //key = the last segment of PathName aka. the imported object.
        //val = the actual imported object
        List<MacroCall> later = new ArrayList<>();
        for (MacroCall macroCall : callFinder.getCalls()) {
            if (MACRO_IMPORT_NAME.equals(macroCall.macroName().getActualIdent())) {
                TokenStream input = macroCall.input();
                PathName imported = PathName.fromTokens(input);
                replacements.add(new Replacement(macroCall.span().getStartCharOffset() - 1, macroCall.span().getEndCharOffset(), "\n\n"));
                if (imported.isEmpty()) {
                    continue;
                }
                imports.put(imported.getActualIdent(), imported);
            } else {
                later.add(macroCall);
            }
        }

        for (MacroCall call : later) {
            handleActualMacroCall(call, imports);
        }

        replacements.sort(Comparator.comparingInt(Replacement::startCharOffset).reversed());
        replacements.forEach(this::replaceMacroCall);

        return fileBuilder.toString();
    }

    private void handleActualMacroCall(MacroCall call, HashMap<String, PathName> imports) throws Exception {
        PathName name = call.macroName();
        String importRef = name.getFirst();
        PathName object = imports.get(importRef);
        if (object != null) {
            PathName actual = object.union(name);
            String qualifiedName = actual.getQualifiedName();
            for (CallableMacro callableMacro : collector.getCallableMacros()) {
                String callableName = callableMacro.qualifiedName();
                if (callableName.equals(qualifiedName)) {
                    //macro import found
                    Method macro = callableMacro.method();
                    TokenStream output = (TokenStream) macro.invoke(null, call.input());
                    Span span = call.span();
                    int outputRows = 1;
                    int inputRows = span.getEndRow() - span.getStartRow();
                    int newlinesCount = inputRows - outputRows + 1 + 1;
                    String newlines = "\n".repeat(newlinesCount);
                    String replacement = expandTokens(output) + newlines;
                    replacements.add(new Replacement(span.getStartCharOffset() - 1, span.getEndCharOffset(), replacement));
                    return;
                }
            }
        }
        throw new NameNotFoundException("Macro " + name + " was not imported!");
    }

    private void replaceMacroCall(Replacement replacement) {
        int start = replacement.startCharOffset();
        int end = replacement.endCharOffset();
        fileBuilder.replace(start, end, replacement.replacement());
    }

    private String expandTokens(TokenStream tokens) {
        StringBuilder builder = new StringBuilder();
        for (Token token : tokens) {
            builder.append(token.repr());
        }
        return builder.toString();
    }
}
