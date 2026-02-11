import dev.v22.jmacros.TokenStream;
import dev.v22.jmacros.tokenizer.JavaTokenizer;
import dev.v22.jmacros.tokens.Token;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;

public class TokenizeTest {
    public static void main(String[] args) throws Exception {
        String s;
        try (Reader r = new FileReader("C:\\Users\\v22ju\\Desktop\\coding\\java\\JMacros\\java-tokenizer\\src\\test\\java\\TestSource.java")) {
            s = r.readAllAsString();
        }

        JavaTokenizer tokenizer = new JavaTokenizer();
        tokenizer.setSource(s.toCharArray(), Path.of("test.java"));
        TokenStream tokens = tokenizer.tokenize();
        for (Token token : tokens) {
            System.out.println(token.repr());
        }
    }
}
