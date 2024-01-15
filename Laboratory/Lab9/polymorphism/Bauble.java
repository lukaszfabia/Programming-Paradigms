package Laboratory.Lab9.polymorphism;

// bombka
public abstract class Bauble {
    protected String color;
    protected String name;
    protected String shape;

    public Bauble(String color, String name, String shape) {
        this.name = name;
        this.color = color;
        this.shape = shape;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s", this.name, this.color, this.shape);
    }
}