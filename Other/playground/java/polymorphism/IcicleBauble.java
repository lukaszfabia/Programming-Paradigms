package Other.playground.java.polymorphism;

// sopel
public class IcicleBauble extends Bauble{
    private double height; 

    public IcicleBauble(String color, String name, String shape, double height) {
        super(color, name, shape);
        this.height = height;
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + String.format("%.2f", this.height);
    }
}
