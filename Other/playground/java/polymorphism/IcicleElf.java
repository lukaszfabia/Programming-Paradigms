package Other.playground.java.polymorphism;

public class IcicleElf extends Elf {
    private int boxPointer;

    public IcicleElf(String nickname) {
        super(nickname);
        this.baubles = new IcicleBauble[CAPACITY];
        this.boxPointer = 0;
    }

    @Override
    public void collect(Bauble currentBauble) {
        if (currentBauble instanceof IcicleBauble){
            if (boxPointer < CAPACITY){
                baubles[boxPointer] = currentBauble;
                boxPointer++;
                System.out.println("its basic bauble time, my dears!");
            } else{
                System.out.println("Box is full!");
            }
        }
    }
    
}
