package Other.playground.java;

import java.util.LinkedList;
import java.util.List;

public class Main {
    static Elf basicElf = new CasualElf("TONY");
    static Elf icicleElf = new IcicleElf("ANTHONY"); 
    static Elf []workers = new Elf[]{basicElf, icicleElf};
    
    public static void main(String[] args) {
        List<Bauble> baubles = new LinkedList<>();
        baubles.add(new BasicBauble("red", "bauble with son name", "Spehere", 5));
        baubles.add(new IcicleBauble("white", "sopelek", "cone", 5.5));
        baubles.add(new BasicBauble("green", "bauble with daughter name", "Star", 3));
        baubles.add(new IcicleBauble("black", "other sopelek", "cone", 10.3));
        baubles.add(new BasicBauble("blue", "bauble with pet name", "Heart", 4));
        baubles.add(new IcicleBauble("silver", "sparkling icicle", "icicle", 7.2));
        baubles.add(new BasicBauble("gold", "bauble with friend name", "Bell", 6));
        baubles.add(new IcicleBauble("purple", "fancy icicle", "icicle", 8.5));
        baubles.add(new BasicBauble("pink", "bauble with parent name", "Angel", 4.5));
        baubles.add(new IcicleBauble("yellow", "glowing icicle", "icicle", 6.8));
        baubles.add(new BasicBauble("orange", "bauble with sibling name", "Candy", 3.5));
        
        letsGo(baubles);
    }

    public static void letsGo(List<Bauble> baubles){
        for (Bauble bauble : baubles) {
            for (Elf worker : workers) {
                worker.collect(bauble);
            }
        }
    }
}
