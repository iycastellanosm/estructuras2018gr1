import java.util.*;

public class Chain<T> implements LinearList<T>, Iterable<T> {
    protected ChainNode<T> firstNode;
    protected int size;

    public Chain(){
        firstNode = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    void checkIndex(int i){
        if(i<0 || i>=size)
            throw new IndexOutOfBoundsException("index = " + i + " size = " + size);
    }
    public T get(int i){
        checkIndex(i);
        ChainNode<T> currentNode = firstNode;
        for(int j = 0; j<i; j++)
            currentNode = currentNode.next;
        return currentNode.element;
    }
    public int indexOf(T x){
        ChainNode<T> currentNode = firstNode;
        int i = 0;
        while(currentNode != null && !currentNode.element.equals(x)){
            currentNode = currentNode.next;
            i++;
        }
        if(currentNode == null)
            return -1;
        else
            return i;
    }
    public T remove(int i){
        checkIndex(i);
        T removedElement;
        if(i == 0){
            removedElement = firstNode.element;
            firstNode = firstNode.next;
        }
        else{
            ChainNode<T> q = firstNode;
            for(int j = 0; j< i - 1; j++)
                q = q.next;
            removedElement = q.next.element;
            q.next = q.next.next;
        }
        size--;
        return removedElement;
    }
    public void add(int i, T x){
        if(i<0 || i>size)
            throw new IndexOutOfBoundsException("index = " + i + " size = " + size);
        if(i==0)
            firstNode = new ChainNode<T> (x, firstNode);
        else{
            ChainNode<T> p = firstNode;
            for(int j=0; j<i-1; j++)
                p = p.next;
            p.next = new ChainNode<T>(x, p.next);
        }
        size++;
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
        return new ChainIterator(firstNode);
    }
    public static void main(String[] args){
        Chain<Integer> x = new Chain<>();
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
            System.out.print( y.next() + " ");
        System.out.println();
        int index = x.indexOf(new Integer(4));
        if(index < 0)
            System.out.println("4 not found");
        else System.out.println("The index of 4 is " + index);
        index = x.indexOf(new Integer(3));
        if(index < 0)
            System.out.println("3 not found");
        else 
        	System.out.println("The index of 3 is " + index);
        System.out.println("Element at 0 is " + x.get(0));
        System.out.println("Element at 3 is " + x.get(3));
        System.out.println(x.remove(1) + " removed");
        System.out.println("The list is " + x);
        System.out.println(x.remove(2) + " removed");
        System.out.println("The list is " + x);
        if(x.isEmpty())
            System.out.println("The list is empty");
        else 
        	System.out.println("The list is not empty");
        System.out.println("List size is " + x.size());
    }
}
