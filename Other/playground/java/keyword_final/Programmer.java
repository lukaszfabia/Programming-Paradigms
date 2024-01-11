package Other.playground.java.keyword_final;

public final class Programmer extends Person{
    private String favLang_;

    // klasa oznaczona jako final nie moze byc rozszerzona
    // class BackendDev extends Programmer {}

    Programmer(String name, int age, String favLang) {
        super(name, age);
        this.favLang_ = favLang;
        //TODO Auto-generated constructor stub
    }

    // nie mozna overridowac metody ozaczonej jako final 
    // public void print(){
    //     System.out.println(this.name_ + " " + this.age_  +" " + this.favLang_);
    // }
    
}
