import java.util.*;

public class LinkedStack<T> implements Stack<T> {
    protected ChainNode<T> topNode;
    public LinkedStack(){
        topNode = null;
    }
    public boolean isEmpty(){
        return topNode == null;
    }
    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return topNode.element;
    }
    public void push( T theElement){
        topNode = new ChainNode<T>(theElement, topNode);
    }
    public T pop(){
        if(isEmpty()) throw new EmptyStackException();
        T topElement = topNode.element;
        topNode = topNode.next;
        return topElement;
    }
    public static void main(String[] args){
        LinkedStack<Integer> s = new LinkedStack<>();
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
