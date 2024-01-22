package Laboratory.Lab9.polymorphism;

// bombka
public abstract class Bauble {
    protected String color_;
    protected String name_;
    protected String shape_;

    public Bauble(String color, String name, String shape) {
        this.name_ = name;
        this.color_ = color;
        this.shape_ = shape;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s", this.name_, this.color_, this.shape_);
    }
}