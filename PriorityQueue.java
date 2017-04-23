public interface PriorityQueue<T extends Comparable<T>> {
    public void add(T x);
    public boolean isEmpty();
    public T peekMin();
    public T removeMin();
}
