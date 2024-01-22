package Laboratory.Lab9.polymorphism;

public abstract class Elf {
    protected static final int CAPACITY = 2; // ilosc pudel 
    protected Bauble[][] baubles_; // stos pudel
    protected final String nickname_;
    protected int boxPointer_;
    protected int boxSize_;
    protected int nextBoxPointer_;
    protected int amountOfBoxes_;

    public Elf(String nickname) {
        this.nickname_ = nickname;
        this.boxPointer_ = 0;
        this.boxSize_ = CAPACITY;
    }

    public Elf(String nickname, int size, int amountOfBoxes) {
        this.nickname_ = nickname;
        this.boxPointer_ = 0;
        this.boxSize_ = size;
        this.amountOfBoxes_ = amountOfBoxes;
    }

    public abstract boolean collect(Bauble currentBauble);

    protected boolean setBauble(Bauble currentBauble){
        if (nextBoxPointer_ < amountOfBoxes_){
            if (boxPointer_ < boxSize_) {
                baubles_[boxPointer_][nextBoxPointer_] = currentBauble;
                boxPointer_++;
                System.out.println(this.nickname_ + ": collected " + currentBauble.name_);
                return true;
            } else {
                boxPointer_ = 0;
                nextBoxPointer_ ++;
            }
        } else {
            System.out.println(this.nickname_ + ": my boxes are full!");
        }
        return false;
    }

    // public boolean isFullBox() {
    //     return this.boxPointer_ >= boxSize_;
    // }

    @Override
    public String toString() {
        return this.nickname_ + " " + baubles_.toString();
    }

    public String getName() {
        return this.nickname_;
    }
}
