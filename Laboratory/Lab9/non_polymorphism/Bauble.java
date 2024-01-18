package Laboratory.Lab9.non_polymorphism;

public class Bauble {
    private String color_;
    private String name_;
    private BoxType type_;
    private String pattern_;
    private double height_;
    private String kindOfMushroom_;

    // cos w stylu konstruktora dla bombki astrakcyjnej
    private Bauble(String color, String name, BoxType type) {
        this.type_ = type;
        this.name_ = name;
        this.color_ = color;
    }

    // dla kuli basic i duzej
    public Bauble(String color, String name, BoxType type, String pattern) {
        this(color, name, type);
        this.pattern_ = pattern;
    }

    // dla sopelka
    public Bauble(String color, String name, BoxType type, double height) {
        this(color, name, type);
        this.height_ = height;
    }

    // dla grzybka
    public Bauble(String color, String name, BoxType type, String kindOfMushroom, String pattern) {
        this(color, name, type);
        this.kindOfMushroom_ = kindOfMushroom;
        this.pattern_ = pattern;
    }

    // jesli ma byc tak jak w poliformicznym
    @Override
    public String toString() {
        switch (type_) {
            case Icicle:
                return String.format("Name: %-10s Color: %-10s Type: %-10s Height: %-10.2f", this.name_, this.color_,
                        this.type_, this.height_);

            case Basic:
            case BigBauble:
                return String.format("Name: %-10s Color: %-10s Type: %-10s Pattern: %-10s", this.name_, this.color_,
                        this.type_, this.pattern_);

            case Mushroom:
                return String.format("Name: %-10s Color: %-10s Type: %-10s Kind of mushroom: %-10s Pattern: %-10s",
                        this.name_, this.color_,
                        this.type_, this.kindOfMushroom_, this.pattern_);
            default:
                return String.format("Name: %-10s Color: %-10s Type: %-10s", this.name_, this.color_,
                        this.type_);
        }
    }

    public BoxType getType() {
        return type_;
    }

    public String getName() {
        return name_;
    }
}
