package Laboratory.Lab9.non_polymorphism;

public class Elf {
    private static int CAPACITY = 1;
    private int boxPointer_;
    private Bauble[] baubles_;
    private BaubleType boxType_;
    private String nickname_;

    public Elf(String nickname, BaubleType role) {
        this.boxType_ = role;
        this.baubles_ = new Bauble[CAPACITY];
        this.boxPointer_ = 0;
        this.nickname_ = nickname;
    }

    public boolean collect(Bauble bauble) {
        if (bauble.getType() == boxType_) {
            if (boxPointer_ < CAPACITY) {
                baubles_[boxPointer_] = bauble;
                boxPointer_++;
                System.out.println(this.nickname_ + ": collected " + bauble.getName());
                return true;
            } else {
                System.out.println(this.nickname_ + ": my box is full!");
            }
        }
        return false;
    }
}
