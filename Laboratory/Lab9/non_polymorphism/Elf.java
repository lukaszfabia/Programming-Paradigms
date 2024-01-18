package Laboratory.Lab9.non_polymorphism;

import java.util.Arrays;

public class Elf {
    private static final int CAPACITY = 2;
    private int boxPointer_;
    private Bauble[] baubles_;
    private BoxType boxType_;
    private String nickname_;

    public Elf(String nickname, BoxType role) {
        this.boxType_ = role;
        this.baubles_ = new Bauble[CAPACITY];
        this.boxPointer_ = 0;
        this.nickname_ = nickname;
    }

    // lepszym podejsciem jest napisanie metody collect dla wszystkich typów i
    // leciec switchem po typie bombki
    // podstawowej wersji collect i wywoloywac odpowiednia podmetode collect
    // wtedy kod jest bardziej rozszerzalny
    public boolean collect(Bauble bauble) {
        if (isMixed(bauble, new BoxType[] { BoxType.BigBauble, BoxType.Basic })) {
            return setBaubleToBox(bauble);
        } else if (bauble.getType() == boxType_) {
            return setBaubleToBox(bauble);
        }

        return false;
    }

    private boolean isMixed(Bauble bauble, BoxType[] avaiableTypes) {
        boolean resultForDemandTypes = Arrays.asList(avaiableTypes).stream()
                .anyMatch(elem -> elem.equals(bauble.getType()));

        return boxType_ == BoxType.Mixed && resultForDemandTypes;
    }

    private boolean setBaubleToBox(Bauble bauble) {
        if (boxPointer_ < CAPACITY) {
            baubles_[boxPointer_] = bauble;
            boxPointer_++;
            System.out.println(this.nickname_ + ": collected " + bauble.getName());
            return true;
        } else {
            System.out.println(this.nickname_ + ": my box is full!");
        }
        return false;
    }

    public String getName() {
        return nickname_;
    }
}
