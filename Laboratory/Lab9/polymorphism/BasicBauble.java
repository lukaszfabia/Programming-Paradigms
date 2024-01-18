package Laboratory.Lab9.polymorphism;

public class BasicBauble extends MixedBauble {
    private String pattern_;

    public BasicBauble(String color, String name, String shape, String pattern) {
        super(color, name, shape);
        this.pattern_ = pattern;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("%-2s", this.pattern_);
    }
}
