interface MyFunction {
    int calculate(int a, int b);
}

public class Main{
    public static void main(String[] args) {
        MyFunction lambda = (a, b) -> a + b;
        int result = lambda.calculate(3, 4);
        System.out.println(result);
    }
}
