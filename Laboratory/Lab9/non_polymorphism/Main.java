package Laboratory.Lab9.non_polymorphism;

import java.util.LinkedList;
import java.util.List;

public class Main {
    static Elf[] workers = new Elf[] { new Elf("Kiss-Shot Acerola-Orion Heart-Under-Blade", BoxType.Mixed),
            new Elf("MARTINEZ", BoxType.Icicle) };

    public static void main(String[] args) {
        List<Bauble> list = new LinkedList<>();
        list.add(new Bauble("red", "basic", BoxType.Basic, "loli girls"));
        list.add(new Bauble("red", "big bauble", BoxType.BigBauble, 52.1));
        list.add(new Bauble("red", "basic", BoxType.Basic, "stars"));
        list.add(new Bauble("red", "big mommy", BoxType.BigBauble, 0.12));
        // list.add(new Bauble("red", "bombka5", BoxType.Mushroom));

        list.stream().forEach(System.out::println);

        letsGo(list);
    }

    public static void letsGo(List<Bauble> baubles) {
        baubles.stream().forEach(bauble -> {
            boolean passedToColleague = false;
            int i = 0;
            // szukanie elfa zeby dopsowac bombke
            while (!passedToColleague && i < workers.length) {
                passedToColleague = workers[i].collect(bauble);
                i++;
            }
            // jak nie to jebut
            if (!passedToColleague) {
                System.out.println(bauble.getName() + ": has been destroyed by " + workers[i - 1].getName() + "!");
            }
        });
    }
}
