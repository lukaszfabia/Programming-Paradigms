package Laboratory.Lab9.polymorphism;

public class BigElf extends Elf{
    
    public BigElf(String nickname) {
        super(nickname);
        this.baubles_ = new BigBauble[boxSize_][CAPACITY];
    }

    public BigElf(String nickname, int size, int amountOfBoxes) {
        super(nickname, size, amountOfBoxes);
        this.baubles_ = new BigBauble[boxSize_][amountOfBoxes];
    }

    @Override
    public boolean collect(Bauble currentBauble) {
        if (currentBauble instanceof BigBauble) {
            return setBauble(currentBauble);
        }
        return false;
    }
    
}
