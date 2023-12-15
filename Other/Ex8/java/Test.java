public class Test {
    int zawartość = 0;

    static void argNiemodyfikowalny(final Test zmienna) {
        zmienna.zawartość = 12;
        // zmienna = null;
    }

    static void argModyfikowalny(Test zmienna) {
        zmienna.zawartość = 1;
        zmienna = null;
    }

    public static void main(String[] args) {
        Test modyfikowalna = new Test();
        final Test niemodyfikowalna = new Test();
        // tutaj wstaw instrukcje

        argNiemodyfikowalny(modyfikowalna);
        System.out.println(modyfikowalna.zawartość);

        argNiemodyfikowalny(niemodyfikowalna);
        System.out.println(niemodyfikowalna.zawartość);

        argModyfikowalny(modyfikowalna);
        System.out.println(modyfikowalna.zawartość);

        argModyfikowalny(niemodyfikowalna);
        System.out.println(niemodyfikowalna.zawartość);
    }
}