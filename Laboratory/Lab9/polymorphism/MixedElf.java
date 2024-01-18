package Laboratory.Lab9.polymorphism;

public class MixedElf extends Elf {

    public MixedElf(String nickname) {
        super(nickname);
        this.baubles_ = new MixedBauble[CAPACITY];
    }

    @Override
    public boolean collect(Bauble currentBauble) {
        if (currentBauble instanceof BasicBauble || currentBauble instanceof IcicleBauble) {
            if (boxPointer_ < CAPACITY) {
                baubles_[boxPointer_] = currentBauble;
                boxPointer_++;
                System.out.println(this.nickname_ + ": collected " + currentBauble.name);
                return true;
            } else {
                System.out.println(this.nickname_ + ": my box is full!");
            }
        }
        return false;
    }

}
