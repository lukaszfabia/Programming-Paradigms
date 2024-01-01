package Other.playground.java;

public class CasualElf extends Elf{
    private int boxPointer;

    public CasualElf(String nickname) {
        super(nickname);
        this.baubles = new BasicBauble[CAPACITY];
        this.boxPointer = 0;
    }

    @Override
    public void collect(Bauble currentBauble) {
        if (currentBauble instanceof BasicBauble && boxPointer < CAPACITY){
            baubles[boxPointer] = currentBauble;
            boxPointer++;
            System.out.println("its basic bauble time, my dears!");
        }else if (boxPointer >= CAPACITY) {
            System.out.println("Box is full!");
        }
    }
    
}
