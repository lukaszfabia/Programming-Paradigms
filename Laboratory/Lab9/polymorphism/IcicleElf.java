package Laboratory.Lab9.polymorphism;

public class IcicleElf extends Elf {

    public IcicleElf(String nickname) {
        super(nickname);
        this.baubles_ = new IcicleBauble[CAPACITY];
    }

    @Override
    public boolean collect(Bauble currentBauble) {
        if (currentBauble instanceof IcicleBauble) {
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
