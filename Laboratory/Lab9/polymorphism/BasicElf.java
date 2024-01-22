package Laboratory.Lab9.polymorphism;

public class BasicElf extends Elf {

    public BasicElf(String nickname) {
        super(nickname);
        this.baubles_ = new BasicBauble[boxSize_][CAPACITY];
    }

    public BasicElf(String nickname, int size, int amountOfBoxes) {
        super(nickname, size, amountOfBoxes);
        this.baubles_ = new BasicBauble[boxSize_][amountOfBoxes_];
    }

    @Override
    public boolean collect(Bauble currentBauble) {
        if (currentBauble instanceof BasicBauble) {
            return setBauble(currentBauble);
        }
        return false;
    }
}
