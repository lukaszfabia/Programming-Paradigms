package Other.playground.java.non_polymorphism;

public class Elf {
    private static int CAPACITY = 1;
    private int boxPointer;
    private Bauble []baubles;
    private BaubleType boxType;

    public Elf(BaubleType role) {
        this.boxType = role;
        this.baubles = new Bauble[CAPACITY];
        this.boxPointer = 0;
    }

    public void collect(Bauble bauble) {
        if (bauble.getType() == boxType) {
            if (boxPointer < CAPACITY) {
                baubles[boxPointer] = bauble;
                boxPointer++;
                System.out.println("here we go, it was " + boxType);
            } else {
                System.out.println("Box is full!");
            }
        }
    }
}
