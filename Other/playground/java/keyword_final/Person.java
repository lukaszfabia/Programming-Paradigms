package Other.playground.java.keyword_final;

public class Person {
    protected String name_;
    protected final int age_;

    Person(String name, int age){
        this.age_ = age;
        this.name_ = name;
    }

    public final void print(){
        System.out.println(name_ + " " + age_);
    }

    public void setName(String name){
        this.name_ = name;
    }

    // zmienna oznaczona jako final moze miec tylko jedno przpisanie
    // public void setAge(int age){
    //     this.age_ = age;
    // }
}
