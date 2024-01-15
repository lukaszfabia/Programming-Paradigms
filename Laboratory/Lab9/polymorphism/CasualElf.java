package Laboratory.Lab9.polymorphism;

public class CasualElf extends Elf {

    public CasualElf(String nickname) {
        super(nickname);
        this.baubles_ = new BasicBauble[CAPACITY];
    }

    @Override
    public boolean collect(Bauble currentBauble) {
        if (currentBauble instanceof BasicBauble) {
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
