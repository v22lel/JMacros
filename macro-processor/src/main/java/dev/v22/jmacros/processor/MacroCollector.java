package dev.v22.jmacros.processor;

import dev.v22.jmacros.macro.Macro;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MacroCollector {
    private final URLClassLoader classLoader;
    private final List<CallableMacro> callableMacros;

    public MacroCollector(URLClassLoader classLoader) {
        this.classLoader = classLoader;
        this.callableMacros = new ArrayList<>();
    }

    public void scanClassFiles() throws URISyntaxException, IOException {
        Path classesDir = Paths.get(classLoader.getURLs()[0].toURI());

        Files.walk(classesDir)
                .filter(p -> p.toString().endsWith(".class"))
                .forEach(classFile -> {
                    try {
                        String fqn = toFqn(classesDir, classFile);
                        Class<?> clazz = classLoader.loadClass(fqn);

                        for (Method m : clazz.getDeclaredMethods()) {
                            if (Modifier.isStatic(m.getModifiers()) && m.isAnnotationPresent(Macro.class)) {
                                callableMacros.add(new CallableMacro(fqn + "." + m.getName(), m));
                            }
                        }

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static String toFqn(Path baseDir, Path classFile) {
        Path relative = baseDir.relativize(classFile);
        return relative.toString()
                .replace(File.separatorChar, '.')
                .replaceAll("\\.class$", "");
    }

    public void scanMacrosJar(Path jarPath) throws IOException, ClassNotFoundException {
        try (JarFile jar = new JarFile(jarPath.toFile())) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    String fqn = entry.getName()
                            .replace('/', '.')
                            .replaceAll("\\.class$", "");
                    Class<?> clazz = classLoader.loadClass(fqn);

                    for (Method m : clazz.getDeclaredMethods()) {
                        if (Modifier.isStatic(m.getModifiers()) && m.isAnnotationPresent(Macro.class)) {
                            callableMacros.add(new CallableMacro(fqn + "." + m.getName(), m));
                        }
                    }
                }
            }
        }
    }

    public List<CallableMacro> getCallableMacros() {
        return callableMacros;
    }
}
