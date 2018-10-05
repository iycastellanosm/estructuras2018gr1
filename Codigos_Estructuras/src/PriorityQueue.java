public interface PriorityQueue<T extends Comparable <? super T>>{
    boolean isEmpty();
    int size();
    void add(T x);
    T getMax();
    T removeMax();
}
