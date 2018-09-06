import java.util.*;

public class DerivedArrayStack <T> extends ArrayLinearList<T> implements Stack<T>{
    public DerivedArrayStack(int initialCapacity){
        super(initialCapacity);
    }
    public DerivedArrayStack(){
        this(10);
    }
    public boolean isEmpty(){
        return super.isEmpty();
    }
    public T peek(){
        if(isEmpty())
            throw new EmptyStackException();
        return get(size() - 1);
    }
    public void push(T theElement){
        add(size(), theElement);
    }
    public T pop(){
        if(isEmpty())
            throw new EmptyStackException();
        return remove(size() - 1 );
    }
    public static void main(String[] args){
        int x;
        DerivedArrayStack<Integer> s = new DerivedArrayStack<>(3);
        s.push(new Integer(1));
        s.push(new Integer(2));
        s.push(new Integer(3));
        s.push(new Integer(4));
        while (!s.isEmpty()){
            System.out.println("Top element is " + s.peek());
            System.out.println("Removed element is " + s.pop());
        }
    }
}
