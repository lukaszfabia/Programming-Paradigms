public class Main {
    public static void main(String[] args) throws EmptyException, FullException {
        MyQueue<Integer> ints = new CircularQueue<Integer>(4);
        ints.enqueue(11); // 0
        ints.enqueue(22); // 1
        ints.enqueue(32); // 2
        ints.enqueue(42);
        System.out.println(ints.isFull());
        System.out.println(ints.isEmpty());
        ints.enqueue(12);
        System.out.println(ints.first());
        System.out.println(ints.isFull());
        ints.dequeue();
        System.out.println(ints.first());
        System.out.println(ints.size());
        ints.dequeue();
        ints.dequeue();
        ints.dequeue();
        System.out.println(ints.isEmpty());
        // ints.dequeue();
        System.out.println(ints.isFull());

    }
}
