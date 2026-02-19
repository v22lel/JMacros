package dev.v22.jmacros.tokens;

public abstract class PrimitiveKeywordToken extends KeywordToken {
    protected PrimitiveKeywordToken(Keyword keyword) {
        super(keyword);
    }

    public static class Byte extends PrimitiveKeywordToken { public Byte() { super(Keyword.Byte); } }
    public static class Short extends PrimitiveKeywordToken { public Short() { super(Keyword.Short); } }
    public static class Int extends PrimitiveKeywordToken { public Int() { super(Keyword.Int); } }
    public static class Long extends PrimitiveKeywordToken { public Long() { super(Keyword.Long); } }
    public static class Float extends PrimitiveKeywordToken { public Float() { super(Keyword.Float); } }
    public static class Double extends PrimitiveKeywordToken { public Double() { super(Keyword.Double); } }
    public static class Char extends PrimitiveKeywordToken { public Char() { super(Keyword.Char); } }
    public static class Boolean extends PrimitiveKeywordToken { public Boolean() { super(Keyword.Boolean); } }
    public static class Void extends PrimitiveKeywordToken { public Void() { super(Keyword.Void); } }
}
