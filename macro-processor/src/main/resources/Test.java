import java.util.List;
importmacro!(com.test.Macros.list);
importmacro!(com.test.Macros.include);

public class A {
    @Override
    public static void a() {
        List<Integer> numbers = list![1, 2, 3];
        byte[] testimg = include!("/test.png");
    }
}