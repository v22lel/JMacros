package dev.v22.jmacros.tokenizer;

import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokens.*;

import java.nio.file.Path;
import java.util.ArrayDeque;

public class JavaTokenizer {
    private Path sourceFile;
    private char[] source;
    private int index;
    private int column;
    private int row;

    private ArrayDeque<Token> putback;

    public void setSource(char[] source, Path sourceFile) {
        this.sourceFile = sourceFile;
        this.source = source;
        this.index = 0;
        this.column = 0;
        this.row = 1;

        this.putback = new ArrayDeque<>();
    }

    public TokenStream tokenize() {
        this.index = 0;
        TokenStream ts = new TokenStream();
        try {
            while (true) {
                ts.push(next());
            }
        } catch (dev.v22.jmacros.tokenizer.EOF ignore) {}
        return ts;
    }

    public void putback(Token token) {
        this.putback.addLast(token);
    }

    private char nextChar() throws dev.v22.jmacros.tokenizer.EOF {
        if (index >= source.length) {
            throw new dev.v22.jmacros.tokenizer.EOF();
        }
        char c = source[index++];
        if (c == '\n') {
            row++;
            column = 0;
        } else {
            column++;
        }
        return c;
    }

    private char peekChar() throws dev.v22.jmacros.tokenizer.EOF {
        return peekChar(0);
    }

    private char peekChar(int dist) throws dev.v22.jmacros.tokenizer.EOF {
        if (index + dist >= source.length) {
            throw new dev.v22.jmacros.tokenizer.EOF();
        }
        return source[index + dist];
    }

    private void skipComments() throws dev.v22.jmacros.tokenizer.EOF {
        while (peekChar() == '/') {
            char p = peekChar(1);
            if (p == '/') {
                nextChar();
                nextChar();
                //single line comment
                while (true) {
                    char n = nextChar(); //nextChar is ok cuz its either EOF or comment forever
                    if (n == '\r') {
                        if (peekChar() == '\n') {
                            nextChar();
                            break;
                        }
                    } else if (n == '\n') {
                        break;
                    }
                }
            } else if (p == '*') {
                nextChar();
                nextChar();
                /*comment block*/
                while (true) {
                    char n = nextChar();
                    if (n == '*') {
                        if (peekChar() == '/') {
                            nextChar();
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
    }

    private void skipWhitespaces() throws dev.v22.jmacros.tokenizer.EOF {
        while (true) {
            char p = peekChar();
            if (Character.isWhitespace(p)) {
                nextChar();
            } else {
                break;
            }
        }
    }

    private Token next() throws dev.v22.jmacros.tokenizer.EOF {
        if (!putback.isEmpty()) {
            return putback.removeFirst();
        }

        int startCharOffset = index;
        skipWhitespaces();
        skipComments();
        skipWhitespaces();

        char n = nextChar();
        Token token = switch (n) {
            //operators
            case '+' -> {
                if (peekChar() == '+') {
                    nextChar();
                    yield new OperatorToken.PlusPlus();
                }
                if (peekChar() == '=') {
                    nextChar();
                    yield new OperatorToken.Plus(true);
                }
                yield new OperatorToken.Plus(false);
            }
            case '-' -> {
                if (peekChar() == '-') {
                    nextChar();
                    yield new OperatorToken.MinusMinus();
                }
                if (peekChar() == '=') {
                    nextChar();
                    yield new OperatorToken.Minus(true);
                }
                if (peekChar() == '>') {
                    nextChar();
                    yield new Token.Arrow();
                }
                yield new OperatorToken.Minus(false);
            }
            case '*' -> {
                if (peekChar() == '=') {
                    nextChar();
                    yield new OperatorToken.Mul(true);
                }
                yield new OperatorToken.Mul(false);
            }
            case '/' -> {
                if (peekChar() == '=') {
                    nextChar();
                    yield new OperatorToken.Div(true);
                }
                yield new OperatorToken.Div(false);
            }
            case '%' -> {
                if (peekChar() == '=') {
                    nextChar();
                    yield new OperatorToken.Mod(true);
                }
                yield new OperatorToken.Mod(false);
            }
            case '|' -> {
                if (peekChar() == '=') {
                    nextChar();
                    yield new OperatorToken.Or(true);
                } else if (peekChar() == '|') {
                    yield new LogicalOperatorToken.Or();
                }
                yield new OperatorToken.Or(false);
            }
            case '&' -> {
                if (peekChar() == '=') {
                    nextChar();
                    yield new OperatorToken.And(true);
                } else if (peekChar() == '|') {
                    yield new LogicalOperatorToken.And();
                }
                yield new OperatorToken.And(false);
            }
            case '^' -> {
                if (peekChar() == '=') {
                    nextChar();
                    yield new OperatorToken.Xor(true);
                }
                yield new OperatorToken.Xor(false);
            }
            case '>' -> {
                if (peekChar() == '>') {
                    nextChar();
                    if (peekChar() == '>') {
                        nextChar();
                        if (peekChar() == '=') {
                            nextChar();
                            yield new OperatorToken.UnsignedRsh(true);
                        } else {
                            yield new OperatorToken.UnsignedRsh(false);
                        }
                    } else if (peekChar() == '=') {
                        nextChar();
                        yield new OperatorToken.Rsh(true);
                    } else {
                        yield new OperatorToken.Rsh(false);
                    }
                } else if (peekChar() == '=') {
                    nextChar();
                    yield new LogicalOperatorToken.Greater(true);
                }
                yield new LogicalOperatorToken.Greater(false);
            }
            case '<' -> {
                if (peekChar() == '<') {
                    nextChar();
                    if (peekChar() == '=') {
                        yield new OperatorToken.Lsh(true);
                    } else {
                        yield new OperatorToken.Lsh(false);
                    }
                } else if (peekChar() == '=') {
                    yield new LogicalOperatorToken.Less(true);
                }
                yield new LogicalOperatorToken.Less(false);
            }
            case '.' -> {
                if (!Character.isDigit(peekChar())) {
                    yield new Token.Dot();
                } else {
                    yield numberToken(n);
                }
            }
            case ':' -> {
                if (peekChar() == ':') {
                    nextChar();
                    yield new Token.ColonColon();
                }
                yield new Token.Colon();
            }
            case ',' -> new Token.Comma();
            case ';' -> new Token.Semicolon();
            case '!' -> new Token.ExclamationMark();
            case '?' -> new Token.QuestionMark();
            case '(' -> new Token.LeftParen();
            case ')' -> new Token.RightParen();
            case '[' -> new Token.LeftBracket();
            case ']' -> new Token.RightBracket();
            case '{' -> new Token.LeftBrace();
            case '}' -> new Token.RightBrace();
            case '@' -> new Token.At();
            case '=' -> {
                if (peekChar() == '=') {
                    nextChar();
                    yield new LogicalOperatorToken.EqEq();
                }
                yield new Token.Eq();
            }
            default -> {
                if (n == '"') {
                    //str lit or text block
                    if (peekChar() == '"' && peekChar(1) == '"') {
                        //text block
                        nextChar();
                        nextChar();
                        StringBuilder builder = new StringBuilder();
                        while (true) {
                            char next = nextChar();
                            if (next == '"') {
                                if (peekChar() == '"' && peekChar(1) == '"') {
                                    nextChar();
                                    nextChar();
                                    yield new LiteralToken.TextBlock(builder.toString());
                                }
                            }
                            builder.append(escape(next));
                        }
                    } else {
                        //str lit
                        StringBuilder builder = new StringBuilder();
                        while (true) {
                            char next = nextChar();
                            if (next == '"') {
                                yield new LiteralToken.Str(builder.toString());
                            }
                            builder.append(next);
                        }
                    }
                }

                //check char literals
                if (n == '\'') {
                    n = nextChar();
                    char literal = escape(n);
                    char probablyQuote = nextChar(); //consume '
                    if (probablyQuote != '\'') {
                        yield new ErrorToken(new IllegalStateException("Unclosed char literal!"));
                    }
                    yield new LiteralToken.Char(literal);
                }

                //ident or keyword or number
                if (numberStart(n)) {
                    yield numberToken(n);
                }

                //ident or keyword
                StringBuilder builder = new StringBuilder();
                builder.append(n);
                while (identPart(peekChar())) {
                    n = nextChar();
                    builder.append(n);
                }
                String s = builder.toString();
                Token maybeKeyword = Keyword.tryToCreateToken(s);
                if (maybeKeyword != null) {
                    yield maybeKeyword;
                }
                yield new IdentToken(s);
            }
        };

        token.setSourceFile(sourceFile);
        token.setStartCharOffset(startCharOffset + 1);
        token.setEndCharOffset(index);
        token.setCol(column);
        token.setRow(row);
        return token;
    }

    private char escape(char n) throws dev.v22.jmacros.tokenizer.EOF {
        if (n == '\\') {
            char escaped = nextChar();
            char literal = switch (escaped) {
                case '\\' -> '\\';
                case 't'  -> '\t';
                case 'r'  -> '\r';
                case '0'  -> '\0';
                case 'n'  -> '\n';
                case 'b'  -> '\b';
                case 'f'  -> '\f';
                case '\'' -> '\'';
                case '"'  -> '"';
                case 'u'  -> {
                    int value =
                            (hexof(nextChar()) << 12) |
                                    (hexof(nextChar()) <<  8) |
                                    (hexof(nextChar()) <<  4) |
                                    hexof(nextChar());
                    yield (char) value;
                }
                default -> 'L';
            };
            return literal;
        }
        return n;
    }

    private boolean identPart(char c) {
        return Character.isLetterOrDigit(c) || "_$".indexOf(c) >= 0;
    }

    private int hexof(char c) {
        if (Character.isDigit(c)) {
            return c - '0';
        }
        char hex = Character.toUpperCase(c);
        return hex - 'A' + 10;
    }

    private boolean numberStart(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private boolean numberPart(char c) {
        return Character.isDigit(c) || "._xbe".indexOf(c) >= 0;
    }

    private Token numberToken(char start) throws dev.v22.jmacros.tokenizer.EOF {
        StringBuilder numBuilder = new StringBuilder();
        numBuilder.append(start);
        while (numberPart(peekChar())) {
            numBuilder.append(nextChar());
        }
        String numberStr = numBuilder.toString();
        try {
            if (peekChar() == 'f' || peekChar() == 'F') {
                nextChar();
                //float
                float f = Float.parseFloat(numberStr);
                return new LiteralToken.Float(f);
            } else if (peekChar() == 'L' || peekChar() == 'l') {
                nextChar();
                //long
                long l = Long.parseLong(numberStr);
                return new LiteralToken.Long(l);
            } else if (numberStr.contains(".")) {
                //double
                if (peekChar() == 'D' || peekChar() == 'd') {
                    nextChar();
                }
                double d = Double.parseDouble(numberStr);
                return new LiteralToken.Double(d);
            } else if (numberStr.contains("b")) {
                //byte
                byte b = Byte.parseByte(numberStr);
                return new LiteralToken.Byte(b);
            } else {
                //int
                int i = Integer.parseInt(numberStr);
                return new LiteralToken.Int(i);
            }
        } catch (NumberFormatException e) {
            return new ErrorToken(e);
        }
    }
}
