package Laboratory.Lab9.polymorphism;

public abstract class Elf {
    protected static final int CAPACITY = 4;
    protected Bauble[] baubles_;
    protected final String nickname_;
    protected int boxPointer_;

    public Elf(String nickname) {
        this.nickname_ = nickname;
        this.boxPointer_ = 0;
    }

    public abstract boolean collect(Bauble currentBauble);

    public boolean isFullBox() {
        return boxPointer_ >= CAPACITY;
    }

    @Override
    public String toString() {
        return nickname_ + " " + baubles_.toString();
    }
}
