package Laboratory.Lab9.polymorphism;

public class MixedElf extends Elf {

    public MixedElf(String nickname) {
        super(nickname);
        this.baubles_ = new Bauble[boxSize_][CAPACITY];
    }

    public MixedElf(String nickname, int size, int amountOfBoxes) {
        super(nickname, size, amountOfBoxes);
        this.baubles_ = new Bauble[boxSize_][CAPACITY];
    }

    @Override
    public boolean collect(Bauble currentBauble) {
        if (currentBauble instanceof BasicBauble || currentBauble instanceof BigBauble) {
            return setBauble(currentBauble);
        }
        return false;
    }

}
