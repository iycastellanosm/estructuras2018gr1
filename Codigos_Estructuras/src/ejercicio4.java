import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.Objects;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/*DESCOMENTAR PARA SUBIR AL JUEZ
 * 
class ArrayLinearList<T> implements LinearList<T>, Iterable<T>{
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
}

class ArrayLinearListIterator<T> implements Iterator<T> {
    //fields
    private ArrayLinearList<T> list;
    private int nextIndex;

    //constructor
    public ArrayLinearListIterator(ArrayLinearList<T> theList){
        list = theList;
        nextIndex = 0;
    }

    //methods
    public boolean hasNext(){
        return nextIndex < list.size;
    }

    public T next(){
        if(nextIndex < list.size)
            return list.element[nextIndex++];
        else
            throw new NoSuchElementException("No next element");
    }

    public void remove(){
        throw new UnsupportedOperationException("remove not supported");
    }
}

class ArrayStack<T> implements Stack<T> {
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
}

interface Stack <T>{
    boolean isEmpty();
    T peek ();
    void push (T theObject);
    T pop();
}

interface LinearList <T>{
    boolean isEmpty();
    int size();
    T get(int i);
    int indexOf(T x);
    T remove(int i);
    void add(int i, T x);
    String toString();
}
*/

public class ejercicio4 {
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String tipoexpresion = reader.readLine().toString();
		StringTokenizer st = new StringTokenizer(reader.readLine());
		ArrayLinearList<String> expresion = new ArrayLinearList<> ();
		while(st.hasMoreTokens()) {
			 expresion.add(st.nextToken());
		}
		if(!tipoexpresion.equals("POSTFIJA")) {
			ArrayStack<Character> pilainfija = new ArrayStack<>();
			ArrayLinearList<String> expresion2 = new ArrayLinearList<> ();
			for(int i = 0; i <expresion.size(); i++) {
				if(expresion.get(i).charAt(0) >= '0' && expresion.get(i).charAt(0) <= '9') {
					expresion2.add(expresion.get(i));
				}
				else{
					if(expresion.get(i).equals("(")) {
						pilainfija.push(expresion.get(i).charAt(0));
					}
					if(expresion.get(i).equals(")")) {
						while(!pilainfija.peek().equals('(')) {
							expresion2.add(pilainfija.pop().toString());
						}
						pilainfija.pop();
					}
					if(expresion.get(i).equals("+")) {
						while(!pilainfija.isEmpty() && pilainfija.peek().equals('*')){
							expresion2.add(pilainfija.pop().toString());
						}
						pilainfija.push(expresion.get(i).charAt(0));
					}
					if(expresion.get(i).equals("*")) {
						pilainfija.push(expresion.get(i).charAt(0));
					}
				}
			}
			while(!pilainfija.isEmpty()){
				expresion2.add(pilainfija.pop().toString());
			}
			expresion = expresion2;
		}
		ArrayStack<Long> pilaposfija = new ArrayStack<>();
		for(int i = 0; i <expresion.size(); i++) {
			if(expresion.get(i).charAt(0) >= '0' && expresion.get(i).charAt(0) <= '9') {
				pilaposfija.push(Long.parseLong(expresion.get(i)));
			}
			else{
				Long a = pilaposfija.pop();
				Long b = pilaposfija.pop();
				if(expresion.get(i).equals("+")) {
					pilaposfija.push(a + b);
				}
				else {
					pilaposfija.push(a * b);
				}
			}
		}
		System.out.println(pilaposfija.peek());
	}
}

