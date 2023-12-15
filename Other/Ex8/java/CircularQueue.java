public class CircularQueue<T> implements MyQueue<T> {
    private final int size;
    private int last;
    private T first;
    private static int capacity = 10;
    private T[] elements;

    @SuppressWarnings("unchecked")
    public CircularQueue() {
        this.size = capacity;
        this.elements = (T[]) new Object[capacity];
        this.last = 0;
    }

    @SuppressWarnings("unchecked")
    public CircularQueue(int size) {
        this.size = size + 1;
        this.elements = (T[]) new Object[size];
        this.last = 0;
    }

    @Override
    public void enqueue(T x) {
        if (isFull() || isEmpty()) {
            first = x;
            elements[0] = x;
            System.out.println("Enqueued: " + elements[0] + " Index: " + 0);
        } else {
            elements[last] = x;
            System.out.println("Enqueued: " + elements[last] + " Index: " + last);
            last++;
        }
    }

    @Override
    public void dequeue() throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException();
        } else if (isFull()) {
            last--;
        }

        System.out.println("Dequeued: " + elements[last]);
        elements[last] = null;
        last--;
    }

    @Override
    public T first() throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException();
        } else {
            return first;
        }
    }

    @Override
    public boolean isEmpty() {
        return last == -1;
    }

    @Override
    public boolean isFull() {
        return last == size - 1;
    }

    @Override
    public int size() {
        return last + 1;
    }
}
