package Other.playground.java;

public abstract class Elf {
    protected static final int CAPACITY = 4;
    protected Bauble []baubles;
    protected String nickname;

    public Elf(String nickname){
        this.nickname = nickname;
    }

    public abstract void collect(Bauble currentBauble);
}
