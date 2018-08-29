public interface LinearList <T>{
    boolean isEmpty();
    int size();
    T get(int i);
    int indexOf(T x);
    T remove(int i);
    void add(int i, T x);
    String toString();
}
