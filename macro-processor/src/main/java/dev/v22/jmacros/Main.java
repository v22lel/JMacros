package dev.v22.jmacros;

import dev.v22.jmacros.processor.extract.InvalidMacroCallException;
import dev.v22.jmacros.processor.extract.MacroCall;
import dev.v22.jmacros.processor.extract.MacroCallFinder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws InvalidMacroCallException, IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/Test.java");
        MacroCallFinder finder = new MacroCallFinder(inputStream, Path.of("Test.java"));
        for (MacroCall call : finder.getCalls()) {
            System.out.println(call);
        }
    }
}
