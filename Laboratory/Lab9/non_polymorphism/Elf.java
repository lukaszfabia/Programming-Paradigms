package Laboratory.Lab9.non_polymorphism;

import java.util.Arrays;

public class Elf {
    private static final int CAPACITY = 2; // ilosc pudel domsyslan 
    private int boxPointer_;
    // private Bauble[] baubles_;
    private Bauble[][] baubles_; // stos pudel
    private BoxType boxType_;
    private String nickname_;
    private int boxSize_;
    private int nextBoxPointer_;
    private int amountOfBoxes_;

    public Elf(String nickname, BoxType role) {
        this.boxType_ = role;
        this.boxSize_ = CAPACITY;
        this.baubles_ = new Bauble[boxSize_][CAPACITY];
        this.boxPointer_ = 0;
        this.nickname_ = nickname;
        this.nextBoxPointer_ = 0;
        this.amountOfBoxes_ = CAPACITY;
    }

    public Elf(String nickname, BoxType role, int size, int amountOfBoxes) {
        this.boxType_ = role;
        this.boxPointer_ = 0;
        this.nickname_ = nickname;
        this.boxSize_ = size;
        this.baubles_ = new Bauble[boxSize_][amountOfBoxes];
        this.nextBoxPointer_ = 0;
        this.amountOfBoxes_ = amountOfBoxes;
    }

    // dla mixed elfa
    // public Elf(String nickname, BoxType role, Bauble[][] baubles) {
    //     this.boxType_ = role;
    //     this.boxPointer_ = 0;
    //     this.nickname_ = nickname;
    //     this.boxSize_ = baubles[0].length;
    //     this.baubles_ = baubles;
    //     this.nextBoxPointer_ = 0;
    //     this.amountOfBoxes_ = baubles.length;
    // }

    // lepszym podejsciem jest napisanie metody collect dla wszystkich typów i
    // leciec switchem po typie bombki
    // podstawowej wersji collect i wywoloywac odpowiednia podmetode collect
    // wtedy kod jest bardziej rozszerzalny
    public boolean collect(Bauble bauble) {
        // sprawdzanie przetwarzana bomka jest duza lub zwykla
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
        if (nextBoxPointer_ < amountOfBoxes_){
            if (boxPointer_ < boxSize_) {
                baubles_[boxPointer_][nextBoxPointer_] = bauble;
                boxPointer_++;
                System.out.println(this.nickname_ + ": collected " + bauble.getName());
                return true;
            } else {
                nextBoxPointer_++;
                boxPointer_ = 0;
            } 
        }else {
            System.out.println(this.nickname_ + ": my boxes are full!");
        }
        return false;
    }

    public String getName() {
        return nickname_;
    }
}
