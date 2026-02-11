package dev.v22.jmacros.processor;

import java.lang.reflect.Method;

public record CallableMacro(
        String qualifiedName, //with packages and all
        Method method
) {}
