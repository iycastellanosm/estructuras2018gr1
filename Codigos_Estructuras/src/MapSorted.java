
public interface MapSorted<K extends Comparable <? super K>, E>{
    boolean isEmpty();
    int size();
    boolean containsKey(K key);
    E get(K key);
    void put(K key, E x);
    void remove(K key);
}
