package Other.playground.java.polymorphism;

public class CasualElf extends Elf{
    private int boxPointer;

    public CasualElf(String nickname) {
        super(nickname);
        this.baubles = new BasicBauble[CAPACITY];
        this.boxPointer = 0;
    }

    @Override
    public void collect(Bauble currentBauble) {
        if (currentBauble instanceof BasicBauble){
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
