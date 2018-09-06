import java.util.*;

public class ArrayStack<T> implements Stack<T> {
    int top;
    T[] stack;
    public ArrayStack(int initialCapacity){
        if(initialCapacity < 1)
            throw new IllegalArgumentException("initialCapacity must be >= 1");
        stack = (T[]) new Object[initialCapacity];
        top = -1;
    }
    public ArrayStack(){
        this(10);
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public T peek(){
        if(isEmpty())
            throw new EmptyStackException();
        return stack[top];
    }
    public void push(T theElement){
        if(top == stack.length - 1){
            T[] old = stack;
            stack = (T[]) new Object[2*stack.length];
            System.arraycopy(old, 0, stack, 0, old.length);
        }
        stack[++top] = theElement;
    }
    public T pop(){
        if(isEmpty())
            throw new EmptyStackException();
        T topElement = stack[top];
        stack[top--] = null;
        return topElement;
    }
    public static void main(String[] args){
        int x;
        ArrayStack<Integer> s = new ArrayStack<>();
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
