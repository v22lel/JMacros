package dev.v22.jmacros.processor.extract;

import dev.v22.jmacros.Span;
import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokenizer.JavaTokenizer;
import dev.v22.jmacros.tokens.IdentToken;
import dev.v22.jmacros.tokens.Token;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MacroCallFinder {
    private final TokenStream tokens;
    private int scanPos;

    private final List<MacroCall> calls;

    public MacroCallFinder(InputStream javaSource, Path sourceFile) throws IOException, InvalidMacroCallException {
        InputStreamReader reader = new InputStreamReader(javaSource);
        String raw = reader.readAllAsString();
        JavaTokenizer tokenizer = new JavaTokenizer();
        tokenizer.setSource(raw.toCharArray(), sourceFile);
        tokens = tokenizer.tokenize();

        calls = new ArrayList<>();
        scan();
    }

    private void scan() throws InvalidMacroCallException {
        for (Iterator<Token> iterator = tokens.iterator(); iterator.hasNext(); ) {
            Token token = nextTknFromIter(iterator);
            if (token instanceof IdentToken) {
                int startToken = token.getStartCharOffset();

                int macroStart = scanPos;
                List<Token> nameTokens = new ArrayList<>();
                do {
                    nameTokens.add(token);
                    token = nextTknFromIter(iterator);
                } while (token instanceof IdentToken || token instanceof Token.Dot);
                if (token instanceof Token.ExclamationMark) {
                    //macro call found
                    token = nextTknFromIter(iterator);
                    InputType inputType;
                    if (Objects.requireNonNull(token) instanceof Token.LeftParen) {
                        inputType = InputType.Call;
                    } else if (token instanceof Token.LeftBrace) {
                        inputType = InputType.Block;
                    } else if (token instanceof Token.LeftBracket) {
                        inputType = InputType.Array;
                    } else {
                        throw InvalidMacroCallException.invalidBlockType(token);
                    }

                    InputResult inputResult = collectMacroInput(iterator, inputType);
                    TokenStream input = inputResult.input();
                    Token lastToken = inputResult.closing();
                    try {
                        TokenStream nameStream = TokenStream.ofTokens(nameTokens);
                        PathName macroName = PathName.fromTokens(nameStream);
                        this.calls.add(new MacroCall(
                                new Span(lastToken.getSourceFile(), startToken, lastToken.getEndCharOffset()),
                                macroName,
                                input,
                                inputType,
                                macroStart,
                                input.len() + nameStream.len() + 2 + 1 //brackets + !
                        ));
                    } catch (MalformedObjectException e) {
                        throw new InvalidMacroCallException(e);
                    }
                }
            }
        }
    }

    private Token nextTknFromIter(Iterator<Token> iter) throws InvalidMacroCallException {
        if (!iter.hasNext()) throw InvalidMacroCallException.partial(new IdentToken("Enf of File"));
        scanPos++;
        return iter.next();
    }

    private InputResult collectMacroInput(Iterator<Token> iter, InputType inputType) throws InvalidMacroCallException {
        int depthParen = inputType == InputType.Call ? 1 : 0;
        int depthBrack = inputType == InputType.Array ? 1 : 0;
        int depthBrace = inputType == InputType.Block ? 1 : 0;
        TokenStream result = new TokenStream();
        Token pushing = null;
        Token closing = null;
        while (depthParen + depthBrack + depthBrace > 0) {
            if (pushing != null) {
                result.pushSingle(pushing);
            }
            Token next = nextTknFromIter(iter);
            if (Objects.requireNonNull(next) instanceof Token.LeftParen) {
                depthParen++;
            } else if (next instanceof Token.RightParen) {
                depthParen--;
            } else if (next instanceof Token.LeftBrace) {
                depthBrace++;
            } else if (next instanceof Token.RightBrace) {
                depthBrace--;
            } else if (next instanceof Token.LeftBracket) {
                depthBrack++;
            } else if (next instanceof Token.RightBracket) {
                depthBrack--;
            }
            closing = next;
            pushing = next;
        }
        return new InputResult(result, closing);
    }

    private record InputResult(TokenStream input, Token closing) {}

    public List<MacroCall> getCalls() {
        return calls;
    }
}
