public interface MyQueue<T> {
    public void enqueue(T x);

    public void dequeue() throws EmptyException;

    public T first() throws EmptyException;

    public boolean isEmpty();

    public boolean isFull();

    public int size();
}