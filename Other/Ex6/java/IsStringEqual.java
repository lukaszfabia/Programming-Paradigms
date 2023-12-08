public class IsStringEqual {
    public static void main(String[] args) {
        String s1 = "foo";
        String s2 = "foo";
        // gdy stworzymy sobie dwa stringi o tej samej wartosci to referencje beda rowne
        // string pool
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        String s3 = new String("foo"); // tworzymy nowy obiekt nie w string poolu
        System.out.println(s1 == s3); // wiec referencje nie sa rowne
        System.out.println(s1.equals(s3)); // ale wartosci sa rowne bo equals porownuje wartosci
    }

}