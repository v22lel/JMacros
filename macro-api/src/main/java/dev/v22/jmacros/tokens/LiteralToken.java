package dev.v22.jmacros.tokens;

public abstract class LiteralToken extends Token {
    public static class Str extends LiteralToken {
        private final String string;

        public Str(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }

        @Override
        public String repr() {
            return '\"' + string + '\"';
        }
    }

    public static final class TextBlock extends LiteralToken {
        private final String value;

        public TextBlock(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String repr() {
            return "\"\"\"\n" + value + "\n\"\"\"";
        }
    }

    public static final class Char extends LiteralToken {
        private final char value;

        public Char(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }

        @Override
        public String repr() {
            return "'" + value + "'";
        }
    }

    public static final class Byte extends LiteralToken {
        private final byte value;

        public Byte(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }

        @Override
        public String repr() {
            return String.valueOf(value);
        }
    }

    public static final class Int extends LiteralToken {
        private final int value;

        public Int(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String repr() {
            return Integer.toString(value);
        }
    }

    public static final class Long extends LiteralToken {
        private final long value;

        public Long(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }

        @Override
        public String repr() {
            return value + "L";
        }
    }

    public static final class Float extends LiteralToken {
        private final float value;

        public Float(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }

        @Override
        public String repr() {
            return value + "f";
        }
    }

    public static final class Double extends LiteralToken {
        private final double value;

        public Double(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String repr() {
            return String.valueOf(value);
        }
    }

    public static final class Bool extends LiteralToken {
        private final boolean value;

        public Bool(boolean value) {
            this.value = value;
        }

        public boolean isValue() {
            return value;
        }

        @Override
        public String repr() {
            return Boolean.toString(value);
        }
    }

    public static final class Null extends LiteralToken {

        public static final Null INSTANCE = new Null();

        private Null() {}

        @Override
        public String repr() {
            return "null";
        }
    }
}
