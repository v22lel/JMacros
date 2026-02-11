package dev.v22.jmacros.tokens;

public abstract class KeywordToken extends Token {
    protected final Keyword keyword;

    protected KeywordToken(Keyword keyword) {
        this.keyword = keyword;
    }
    @Override public String repr() {
        return keyword.repr();
    }

    public static class Abstract extends KeywordToken { public Abstract() { super(Keyword.Abstract); } }
    public static class Assert extends KeywordToken { public Assert() { super(Keyword.Assert); } }
    public static class Break extends KeywordToken { public Break() { super(Keyword.Break); } }
    public static class Case extends KeywordToken { public Case() { super(Keyword.Case); } }
    public static class Catch extends KeywordToken { public Catch() { super(Keyword.Catch); } }
    public static class Class extends KeywordToken { public Class() { super(Keyword.Class); } }
    public static class Continue extends KeywordToken { public Continue() { super(Keyword.Continue); } }
    public static class Default extends KeywordToken { public Default() { super(Keyword.Default); } }
    public static class Do extends KeywordToken { public Do() { super(Keyword.Do); } }
    public static class Else extends KeywordToken { public Else() { super(Keyword.Else); } }
    public static class Enum extends KeywordToken { public Enum() { super(Keyword.Enum); } }
    public static class Extends extends KeywordToken { public Extends() { super(Keyword.Extends); } }
    public static class Final extends KeywordToken { public Final() { super(Keyword.Final); } }
    public static class Finally extends KeywordToken { public Finally() { super(Keyword.Finally); } }
    public static class For extends KeywordToken { public For() { super(Keyword.For); } }
    public static class If extends KeywordToken { public If() { super(Keyword.If); } }
    public static class Implements extends KeywordToken { public Implements() { super(Keyword.Implements); } }
    public static class Import extends KeywordToken { public Import() { super(Keyword.Import); } }
    public static class Instanceof extends KeywordToken { public Instanceof() { super(Keyword.Instanceof); } }
    public static class Interface extends KeywordToken { public Interface() { super(Keyword.Interface); } }
    public static class Native extends KeywordToken { public Native() { super(Keyword.Native); } }
    public static class New extends KeywordToken { public New() { super(Keyword.New); } }
    public static class Package extends KeywordToken { public Package() { super(Keyword.Package); } }
    public static class Private extends KeywordToken { public Private() { super(Keyword.Private); } }
    public static class Protected extends KeywordToken { public Protected() { super(Keyword.Protected); } }
    public static class Public extends KeywordToken { public Public() { super(Keyword.Public); } }
    public static class Return extends KeywordToken { public Return() { super(Keyword.Return); } }
    public static class Static extends KeywordToken { public Static() { super(Keyword.Static); } }
    public static class Strictfp extends KeywordToken { public Strictfp() { super(Keyword.Strictfp); } }
    public static class Super extends KeywordToken { public Super() { super(Keyword.Super); } }
    public static class Switch extends KeywordToken { public Switch() { super(Keyword.Switch); } }
    public static class Synchronized extends KeywordToken { public Synchronized() { super(Keyword.Synchronized); } }
    public static class This extends KeywordToken { public This() { super(Keyword.This); } }
    public static class Throw extends KeywordToken { public Throw() { super(Keyword.Throw); } }
    public static class Throws extends KeywordToken { public Throws() { super(Keyword.Throws); } }
    public static class Transient extends KeywordToken { public Transient() { super(Keyword.Transient); } }
    public static class Try extends KeywordToken { public Try() { super(Keyword.Try); } }
    public static class Volatile extends KeywordToken { public Volatile() { super(Keyword.Volatile); } }
    public static class While extends KeywordToken { public While() { super(Keyword.While); } }
    public static class Const extends KeywordToken { public Const() { super(Keyword.Const); } }
    public static class Goto extends KeywordToken { public Goto() { super(Keyword.Goto); } }
    public static class Var extends KeywordToken { public Var() { super(Keyword.Var); } }
    public static class Record extends KeywordToken { public Record() { super(Keyword.Record); } }
    public static class Sealed extends KeywordToken { public Sealed() { super(Keyword.Sealed); } }
    public static class Permits extends KeywordToken { public Permits() { super(Keyword.Permits); } }
    public static class Yield extends KeywordToken { public Yield() { super(Keyword.Yield); } }
}
