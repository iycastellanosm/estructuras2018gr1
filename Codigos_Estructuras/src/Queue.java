public interface Queue<T>{
    boolean isEmpty();
    T getFrontElement();
    void put (T theObject);
    T remove();
}
