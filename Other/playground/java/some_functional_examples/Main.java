package Other.playground.java.some_functional_examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Other.playground.java.polymorphism.CasualElf;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,55,12);
        List<Integer> list2= list.stream().map(x -> x*x).filter(x -> x%2==0).collect(Collectors.toList());
        for (Integer integer : list2) {
            System.out.println(integer);
        }
        Object obj = new Object();
        System.out.println(list.stream().sorted((x, y) -> y.compareTo(x)).collect(Collectors.toList()));
        Tuple<Object, Character> tuple = new Tuple<Object,Character>(null, 'c');
        System.out.println(tuple);
        List<Tuple> tuples = new ArrayList<>();
        tuples.add(new Tuple<String, Integer>("jakis string", 3123));
        tuples.add(new Tuple<Double, CasualElf>(3.231, new CasualElf("Marcin")));
        tuples.stream().forEach(System.out::println);

        System.out.println();
        System.out.println(getPoint(0, 0));
    }

    public static Tuple<Integer, Integer> getPoint(int x, int y){
        return new Tuple<Integer,Integer>(x, y);
    }
} 

