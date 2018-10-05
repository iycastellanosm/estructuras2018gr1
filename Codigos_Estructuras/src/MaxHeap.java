import java.util.*;
public class MaxHeap<T extends Comparable<? super T>> implements PriorityQueue<T> {

    T[] BinaryHeap;
    int size;

    public MaxHeap(int initialCapacity){
        if(initialCapacity < 1)throw new IllegalArgumentException("initialCapacity must be >= 1");
        BinaryHeap = ( T[] ) new Comparable[ initialCapacity + 1];
        size = 0;
    }

    public MaxHeap(){
        this(10);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public T getMax(){
        return (size == 0) ? null : BinaryHeap[1];
    }

    public void add(T x ){
        if(size == BinaryHeap.length - 1){ //The array is full
            T[] old = BinaryHeap;
            BinaryHeap = (T[]) new Comparable[2 * (size+1)];
            System.arraycopy(old, 0, BinaryHeap, 0, size + 1);
        }
        int currentNode = ++size;
        while(currentNode != 1 && BinaryHeap[currentNode / 2].compareTo(x) < 0){
            BinaryHeap[currentNode] = BinaryHeap[currentNode / 2];
            currentNode /=2;
        }
        BinaryHeap [currentNode] = x;
    }

    public T removeMax(){
        if(size == 0) return null;
        T maxElement = BinaryHeap[1];
        T lastElement = BinaryHeap[size--];
        int currentNode = 1;
        int child = 2;
        while(child <= size){
            if(child < size && BinaryHeap[child].compareTo(BinaryHeap[child + 1]) < 0)
                child++;
            if(lastElement.compareTo(BinaryHeap[child]) >=0 )
                break;
            BinaryHeap[currentNode] = BinaryHeap[child];
            currentNode = child;
            child *= 2;
        }
        BinaryHeap[currentNode] = lastElement;
        return maxElement;
    }

    public void initialize(ArrayLinearList<T> theList) {
        size = theList.size();
        BinaryHeap = (T[]) new Comparable [size + 1];
        for(int i=1; i<BinaryHeap.length; i++)
            BinaryHeap[i] = theList.get(i - 1);
        for(int root = size / 2; root >= 1 ;root --) {
            T rootElement = BinaryHeap[root];
            int child = 2 * root;
            while (child <= size) {
                if (child < size && BinaryHeap[child].compareTo(BinaryHeap[child + 1]) < 0)
                    child++;
                if (rootElement.compareTo(BinaryHeap[child]) >= 0)
                    break;
                BinaryHeap[child / 2] = BinaryHeap[child];
                child *= 2;
            }
            BinaryHeap[child / 2] = rootElement;
        }
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("The " + size + " elements are [ ");
        if(size > 0){
            s.append(BinaryHeap[1]);
            for(int i = 2; i <= size ; i++){
                s.append(", " + Objects.toString(BinaryHeap[i]));
            }
        }
        s.append("]");
        return new String(s);
    }

    public static void main(String[] args){
        MaxHeap<Integer> h = new MaxHeap<Integer>(10);
        h.add(new Integer(10));
        h.add(new Integer(20));
        h.add(new Integer(5));
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        h.add(new Integer(15));
        h.add(new Integer(0));
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
        System.out.println(h.removeMax());
    }
}
