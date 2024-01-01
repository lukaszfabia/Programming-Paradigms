package Other.playground.java;

// bombka
public abstract class Bauble {
    protected String color;
    protected String name;
    protected String shape;

    public Bauble(String color, String name, String shape){
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%-2s", this.name, this.color, this.shape); 
    }
}