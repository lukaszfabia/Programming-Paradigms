package Laboratory.Lab9.polymorphism;

public class IcicleElf extends Elf {

    public IcicleElf(String nickname) {
        super(nickname);
        this.baubles_ = new IcicleBauble[boxSize_][CAPACITY];
    }

    public IcicleElf(String nickname, int size, int amountOfBoxes) {
        super(nickname, size, amountOfBoxes);
        this.baubles_ = new IcicleBauble[boxSize_][amountOfBoxes_];
    }

    @Override
    public boolean collect(Bauble currentBauble) {
        if (currentBauble instanceof IcicleBauble) {
            return setBauble(currentBauble);
        }
        return false;
    }
}
