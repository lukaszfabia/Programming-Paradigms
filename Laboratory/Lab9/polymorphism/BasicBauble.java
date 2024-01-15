package Laboratory.Lab9.polymorphism;

public class BasicBauble extends Bauble {
    private double radius;

    public BasicBauble(String color, String name, String shape, double radius) {
        super(color, name, shape);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("%.2f", this.radius);
    }
}
