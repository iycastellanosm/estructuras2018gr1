
public interface SetSorted<T extends Comparable <? super T>>{
    boolean isEmpty();
    int size();
    boolean contains(T x);
    void add(T x);
    void remove(T x);
    T getMin();
    T getMax();
}
