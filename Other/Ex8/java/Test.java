public class Test {
    /*
     * gdyby int byl final to nie moznaby zmienic jego wartosci 
     */
    /*final */ int zawartość = 0;

    /*
     * ta funkcja widzi zmienna jako nie mutowalny obiekt, ale zawartosc nie jest oznaczona jako final 
     * nie ustawi sobie na null bo obiekt jest niemutowalny
     */
    static void argNiemodyfikowalny(final Test zmienna) {
        zmienna.zawartość = 12;
        // zmienna = null;
    }

    /*
     * tutaj jest widziana jako obiekt modyfikowalny i mozna modyfikowac obiekt.
     * ustawi sobie null ale tylko w scopie tej funkcji 
     */
    static void argModyfikowalny(Test zmienna) {
        zmienna.zawartość = 1;
        zmienna = null;
    }

    public static void main(String[] args) {
        Test modyfikowalna = new Test(); // mutowalna zmienna w scali to jest var 
        final Test niemodyfikowalna = new Test(); // nieumutowalna zmienna - odpowiednik value ze scali
        // tutaj wstaw instrukcje

        argNiemodyfikowalny(modyfikowalna); 
        System.out.println(modyfikowalna.zawartość);

        argModyfikowalny(modyfikowalna); 
        System.out.println(modyfikowalna.zawartość);

        argNiemodyfikowalny(niemodyfikowalna);
        System.out.println(niemodyfikowalna.zawartość);

        argModyfikowalny(niemodyfikowalna); 
        System.out.println(niemodyfikowalna.zawartość);
    }


}