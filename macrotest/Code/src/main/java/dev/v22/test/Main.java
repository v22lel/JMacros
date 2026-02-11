package dev.v22.test;

importmacro!(com.test.macros.TestMacros.fmt);

public class Main {
    public static void main(String[] args) {
        String name = "mike";
        int age = 18;
        System.out.println(fmt!("Hello {name}, your age is {age}"));
    }
}
