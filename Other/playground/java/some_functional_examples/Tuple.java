package Other.playground.java.some_functional_examples;

public class Tuple<A, B> {
    A _0;
    B _1;

    public Tuple(){
        this._0 = null;
        this._1 = null;
    }

    public Tuple(A first, B second){
        this._0 = first;
        this._1 = second;
    }

    void set_0(A newFirst){
        this._0 = newFirst;
    }

    void set_1(B newSecond){
        this._1 = newSecond;
    }

    @Override
    public String toString(){
        return "(" + this._0 + ", " + this._1 + ")";
    }
}
