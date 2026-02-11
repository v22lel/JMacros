package dev.v22.jmacros.processor.extract;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.IdentToken;
import dev.v22.jmacros.tokens.OperatorToken;
import dev.v22.jmacros.tokens.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PathName {
    private final List<String> segments;

    private PathName() {
        this.segments = new ArrayList<>();
    }

    public PathName(String... segments) {
        this.segments = Arrays.asList(segments);
    }

    public String getFirst() {
        return segments.getFirst();
    }

    public String getActualIdent() {
        return segments.getLast();
    }

    public int segments() {
        return segments.size();
    }

    public boolean isEmpty() {
        return segments.isEmpty();
    }

    @Override
    public String toString() {
        return String.join(".", segments);
    }

    public static PathName fromTokens(TokenStream tokens) throws MalformedObjectException {
        List<String> segments = new ArrayList<>();
        String current = null;
        for (Token token : tokens) {
            if (Objects.requireNonNull(token) instanceof IdentToken ident) {
                if (current == null) {
                    current = ident.getIdent();
                }
            } else if (token instanceof Token.Dot) {
                if (current != null) {
                    segments.add(current);
                    current = null;
                }
            } else if (token instanceof OperatorToken.Mul) {
                if (current == null) {
                    current = "*";
                }
            } else {
                throw new MalformedObjectException("Only Dot and Ident is allowed in PathName!");
            }
        }
        if (current != null) {
            segments.add(current);
        }
        return new PathName(segments.toArray(new String[0]));
    }

    public PathName extend(PathName other) {
        PathName newName = new PathName();
        newName.segments.addAll(segments);
        newName.segments.addAll(other.segments);
        return newName;
    }

    public String getQualifiedName() {
        return toString();
    }
}
