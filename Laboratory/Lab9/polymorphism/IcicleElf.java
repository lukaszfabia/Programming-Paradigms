package Laboratory.Lab9.polymorphism;

public class IcicleElf extends Elf {
    private int boxPointer;

    public IcicleElf(String nickname) {
        super(nickname);
        this.baubles_ = new IcicleBauble[CAPACITY];
        this.boxPointer = 0;
    }

    @Override
    public boolean collect(Bauble currentBauble) {
        if (currentBauble instanceof IcicleBauble) {
            if (boxPointer < CAPACITY) {
                baubles_[boxPointer] = currentBauble;
                boxPointer++;
                System.out.println(this.nickname_ + ": collected " + currentBauble.name);
                return true;
            } else {
                System.out.println(this.nickname_ + ": my box is full!");
            }
        }
        return false;
    }
}
