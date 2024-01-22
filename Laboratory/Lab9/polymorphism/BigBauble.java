package Laboratory.Lab9.polymorphism;

public class BigBauble extends Bauble {
    private String pattern_;

    public BigBauble(String color, String name, String shape, String pattern) {
        super(color, name, shape);
        this.pattern_ = pattern;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("%-2s", this.pattern_);
    }
}
