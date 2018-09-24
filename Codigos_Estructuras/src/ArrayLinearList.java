import java.util.*;

public class ArrayLinearList<T> implements LinearList<T>, Iterable<T>{
    //fields
    protected T[] element; // array of elements
    protected int size; //number of elements in the array

    //constructors
    public ArrayLinearList(int initialCapacity){
        if(initialCapacity < 1) throw new IllegalArgumentException("Initial Capacity must be >= 1");
        element = (T[]) new Object[initialCapacity];
        size = 0;
    }
    public ArrayLinearList(){
        this(10);
    }

    //methods
    public boolean isEmpty(){
        return size == 0;
    }
    
    public int size(){
        return size;
    }
    
    void checkIndex(int i){
        if(i < 0 || i >= size)
            throw new IndexOutOfBoundsException("index = " + i + " size = " + size);
    }
    
    public T get(int i){
        checkIndex(i);
        return element[i];
    }
    
    public int indexOf(T x){
        for(int i =0; i<size; i++)
            if(element[i].equals(x))
                return i;
        return -1;
    }
    
    public T remove(int i){
        checkIndex(i);
        T removedElement = element[i];
        for(int j = i + 1; j<size; j++)
            element[j - 1] = element[j];
        element[--size] = null;
        return removedElement;
    }
    
    public void add(int i, T x){
        if(i<0 || i>size)
            throw new IndexOutOfBoundsException("index = " + i + " size = " + size);
        if(size == element.length){
            T[] old = element;
            element = (T[]) new Object[2*size];
            System.arraycopy(old, 0, element, 0, size);
        }
        for(int j = size - 1; j>=i; j--)
            element[j+1] = element[j];

        element[i] = x;
        size++;
    }
    
    public void add(T x){
    	add(size, x);
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder("[");
        for(T x: this)
            s.append(Objects.toString(x) + ", ");
        if(size > 0)
            s.setLength(s.length() - 2);
        s.append("]");
        return new String(s);
    }
    
    public Iterator<T> iterator(){
        return new ArrayLinearListIterator<T> (this);
    }
    
    public static void main(String[] args){
        ArrayLinearList<Integer> x = new ArrayLinearList<>();
        System.out.println("Initial size is " + x.size());
        if(x.isEmpty())
            System.out.println("The list is empty");
        else System.out.println("The list is not empty");
        x.add(0, new Integer(2));
        x.add(1, new Integer(6));
        x.add(0, new Integer(1));
        x.add(2, new Integer(4));
        System.out.println("List size is " + x.size());
        System.out.println("The list is " + x);
        Iterator y = x.iterator();
        while(y.hasNext())
            System.out.print(y.next() + " ");
        System.out.println();
        int index = x.indexOf(new Integer(4));
        if(index < 0)
            System.out.println("4 not found");
        else System.out.println("The index of 4 is " + index);
        index = x.indexOf(new Integer(3));
        if(index < 0)
            System.out.println("3 not found");
        else System.out.println("The index of 3 is " + index);
        System.out.println("Element at 0 is " + x.get(0));
        System.out.println("Element at 3 is " + x.get(3));
        System.out.println(x.remove(1) + " removed");
        System.out.println("The list is " + x);
        System.out.println(x.remove(2) + " removed");
        System.out.println("The list is " + x);
        if(x.isEmpty())
            System.out.println("The list is empty");
        else System.out.println("The list is not empty");
        System.out.println("List size is " + x.size());
    }
}
