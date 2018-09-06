import java.util.*;

public class DerivedLinkedStack<T> extends Chain<T> implements Stack<T>{
    public DerivedLinkedStack(){
        super();
    }
    public boolean isEmpty(){
        return super.isEmpty();
    }
    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return get(0);
    }
    public void push(T theElement){
        add(0, theElement);
    }
    public T pop(){
        if(isEmpty()) throw new EmptyStackException();
        return remove(0);
    }
    public static void main(String[] args){
        int x;
        DerivedLinkedStack<Integer> s = new DerivedLinkedStack<>();
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
