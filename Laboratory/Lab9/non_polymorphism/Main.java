package Laboratory.Lab9.non_polymorphism;

import java.util.LinkedList;
import java.util.List;

public class Main {
    static Elf[] workers = new Elf[] { new Elf("Bartosh", BaubleType.Icicle), new Elf("MARTINEZ", BaubleType.Basic) };

    public static void main(String[] args) {
        List<Bauble> list = new LinkedList<>();
        list.add(new Bauble("red", "bombka1", BaubleType.Basic));
        list.add(new Bauble("red", "bombka2", BaubleType.Icicle));
        list.add(new Bauble("red", "bombka3", BaubleType.Basic));
        list.add(new Bauble("red", "bombka4", BaubleType.Icicle));
        list.add(new Bauble("red", "bombka5", BaubleType.Mushroom));

        letsGo(list);
    }

    public static void letsGo(List<Bauble> baubles) {
        for (Bauble bauble : baubles) {
            boolean passedToColleague = false;
            int i = 0;
            while (!passedToColleague && i < workers.length) {
                passedToColleague = workers[i].collect(bauble);
                i++;
            }
            if (!passedToColleague) {
                System.out.println(bauble.getName() + ": has been destroyed!");
            }
        }
    }
}
