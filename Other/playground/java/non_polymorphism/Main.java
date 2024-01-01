package Other.playground.java.non_polymorphism;

import java.util.LinkedList;
import java.util.List;

public class Main {
    static Elf []workers = new Elf[]{new Elf(BaubleType.Icicle), new Elf(BaubleType.Basic)};
    public static void main(String[] args) {
        List<Bauble> list = new LinkedList<>();
        list.add(new Bauble("red", "bombka1", BaubleType.Basic));
        list.add(new Bauble("red", "bombka2", BaubleType.Icicle));
        list.add(new Bauble("red", "bombka3", BaubleType.Basic));      
        list.add(new Bauble("red", "bombka4", BaubleType.Icicle)); 

     

        letGo(list);
    }

    public static void letGo(List<Bauble> list){
        for (Bauble bauble : list) {
            for (Elf elf: workers){
                elf.collect(bauble);
            }
        }
    }
}
