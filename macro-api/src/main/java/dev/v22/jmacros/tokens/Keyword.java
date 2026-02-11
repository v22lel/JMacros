package dev.v22.jmacros.tokens;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum Keyword {
    Abstract("abstract", KeywordToken.Abstract.class),
    Assert("assert", KeywordToken.Assert.class),
    Boolean("boolean", PrimitiveKeywordToken.Boolean.class),
    Break("break", KeywordToken.Break.class),
    Byte("byte", PrimitiveKeywordToken.Byte.class),
    Case("case", KeywordToken.Case.class),
    Catch("catch", KeywordToken.Catch.class),
    Char("char", PrimitiveKeywordToken.Char.class),
    Class("class", KeywordToken.Class.class),
    Continue("continue", KeywordToken.Continue.class),
    Default("default", KeywordToken.Default.class),
    Do("do", KeywordToken.Do.class),
    Double("double", PrimitiveKeywordToken.Double.class),
    Else("else", KeywordToken.Else.class),
    Enum("enum", KeywordToken.Enum.class),
    Extends("extends", KeywordToken.Extends.class),
    Final("final", KeywordToken.Final.class),
    Finally("finally", KeywordToken.Finally.class),
    Float("float", PrimitiveKeywordToken.Float.class),
    For("for", KeywordToken.For.class),
    If("if", KeywordToken.If.class),
    Implements("implements", KeywordToken.Implements.class),
    Import("import", KeywordToken.Import.class),
    Instanceof("instanceof", KeywordToken.Instanceof.class),
    Int("int", PrimitiveKeywordToken.Int.class),
    Interface("interface", KeywordToken.Interface.class),
    Long("long", PrimitiveKeywordToken.Long.class),
    Native("native", KeywordToken.Native.class),
    New("new", KeywordToken.New.class),
    Package("package", KeywordToken.Package.class),
    Private("private", KeywordToken.Private.class),
    Protected("protected", KeywordToken.Protected.class),
    Public("public", KeywordToken.Public.class),
    Return("return", KeywordToken.Return.class),
    Short("short", PrimitiveKeywordToken.Short.class),
    Static("static", KeywordToken.Static.class),
    Strictfp("strictfp", KeywordToken.Strictfp.class),
    Super("super", KeywordToken.Super.class),
    Switch("switch", KeywordToken.Switch.class),
    Synchronized("synchronized", KeywordToken.Synchronized.class),
    This("this", KeywordToken.This.class),
    Throw("throw", KeywordToken.Throw.class),
    Throws("throws", KeywordToken.Throws.class),
    Transient("transient", KeywordToken.Transient.class),
    Try("try", KeywordToken.Try.class),
    Void("void", PrimitiveKeywordToken.Void.class),
    Volatile("volatile", KeywordToken.Volatile.class),
    While("while", KeywordToken.While.class),
    Const("const", KeywordToken.Const.class),
    Goto("goto", KeywordToken.Goto.class),
    Var("var", KeywordToken.Var.class),
    Record("record", KeywordToken.Record.class),
    Sealed("sealed", KeywordToken.Sealed.class),
    Permits("permits", KeywordToken.Permits.class),
    Yield("yield", KeywordToken.Yield.class);
    //NonSealed("non-sealed"), this will be introduced in the parser BECAUSE WHY WOULD YOU EVER HAVE A KEYWORD THAT LOOKS LIKE THIS;

    private final String repr;
    private final Class<? extends KeywordToken> clazz;

    Keyword(String repr, Class<? extends KeywordToken> clazz) {
        this.repr = repr;
        this.clazz = clazz;
    }

    public String repr() {
        return repr;
    }

    public static Token tryToCreateToken(String name) {
        for (Keyword val : values()) {
            if (val.repr.equals(name)) {
                try {
                    Constructor<? extends KeywordToken> c = val.clazz.getConstructor();
                    return c.newInstance();
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ignore) {}
            }
        }
        return null;
    }
}
