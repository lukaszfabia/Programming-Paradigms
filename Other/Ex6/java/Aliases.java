public class Aliases {
    public static void main(String[] args) {
        int[] ints = { 1, 2, 3 };
        // za pomoca petli for each mozemy iterowac po tablicach ale nie mozemy zmieniac
        // wartosci
        for (int i : ints) {
            System.out.println(i);
            i = 0;
        }

        for (int i : ints) {
            System.out.println(i);
        }

        // kopiuje referencje z ints do tablicy ints2
        int[] ints2 = ints;

        // takie przejscie po tablicy pozwala na zmiane wartosci
        for (int i = 0; i < ints2.length; i++) {
            ints2[i] = -1;
        }
        
        // ustawienie []ints2 elementow na -1 spowoduje zmiane elementow dla ints poniewaz
        // ints i ints2 wskazuja na ten sam obszar pamieci 
        for (int i : ints) {
            System.out.println(i);
        }

    }
}