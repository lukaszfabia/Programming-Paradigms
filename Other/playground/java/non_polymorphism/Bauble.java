package Other.playground.java.non_polymorphism;

public class Bauble {
    private String color;
    private String name;
    private BaubleType type;

    public Bauble(String color, String name, BaubleType type){
        this.type = type;
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString(){
        return String.format("Type: %s, Name: %s, Color: %s", this.type, this.name, this.color);
    }

    public BaubleType getType(){
        return type;
    }
}
