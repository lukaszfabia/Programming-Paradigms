package Laboratory.Lab9.non_polymorphism;

public class Bauble {
    private String color_;
    private String name_;
    private BaubleType type_;

    public Bauble(String color, String name, BaubleType type) {
        this.type_ = type;
        this.name_ = name;
        this.color_ = color;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Name: %s, Color: %s", this.type_, this.name_, this.color_);
    }

    public BaubleType getType() {
        return type_;
    }

    public String getName() {
        return name_;
    }
}
