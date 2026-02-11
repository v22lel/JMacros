package dev.v22.jmacros.tokens;

public abstract class PrimitiveKeywordToken extends KeywordToken {
    protected PrimitiveKeywordToken(Keyword keyword) {
        super(keyword);
    }

    public static class Byte extends KeywordToken { public Byte() { super(Keyword.Byte); } }
    public static class Short extends KeywordToken { public Short() { super(Keyword.Short); } }
    public static class Int extends KeywordToken { public Int() { super(Keyword.Int); } }
    public static class Long extends KeywordToken { public Long() { super(Keyword.Long); } }
    public static class Float extends KeywordToken { public Float() { super(Keyword.Float); } }
    public static class Double extends KeywordToken { public Double() { super(Keyword.Double); } }
    public static class Char extends KeywordToken { public Char() { super(Keyword.Char); } }
    public static class Boolean extends KeywordToken { public Boolean() { super(Keyword.Boolean); } }
    public static class Void extends KeywordToken { public Void() { super(Keyword.Void); } }
}
