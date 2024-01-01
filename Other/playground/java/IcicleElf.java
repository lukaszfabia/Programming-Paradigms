package Other.playground.java;

public class IcicleElf extends Elf {
    private int boxPointer;

    public IcicleElf(String nickname) {
        super(nickname);
        this.baubles = new IcicleBauble[CAPACITY];
        this.boxPointer = 0;
    }

    @Override
    public void collect(Bauble currentBauble) {
        if (currentBauble instanceof IcicleBauble && boxPointer < CAPACITY){
            baubles[boxPointer] = currentBauble;
            boxPointer++;
            System.out.println("its icicle turn, my dears!");
        }else if (boxPointer >= CAPACITY) {
            System.out.println("Box is full!");
        }
    }
    
}
