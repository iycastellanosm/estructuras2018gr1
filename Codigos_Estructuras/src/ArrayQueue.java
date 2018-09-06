public class ArrayQueue<T> implements Queue<T>{
    int front;
    int back;
    T[] queue;

    public ArrayQueue(int initialCapacity ){
        if(initialCapacity < 1)
            throw new IllegalArgumentException("initialCapacity must be >= 1");
        queue = (T[]) new Object[initialCapacity + 1];
        front = back = 0;
    }
    public ArrayQueue(){
        this(10);
    }
    public boolean isEmpty(){
        return back == front;
    }
    public T getFrontElement(){
        if(isEmpty()) return null;
        else return queue[(front + 1) % queue.length];
    }
    public void put(T theElement){
        if((back + 1) % queue.length == front){
            T[] newQueue = (T []) new Object[2 * queue.length];
            int start = (front + 1) % queue.length;
            if(start < 2)
                System.arraycopy(queue, start, newQueue, 0, queue.length - 1);
            else{
                System.arraycopy(queue, start, newQueue, 0, queue.length - start);
                System.arraycopy(queue, 0, newQueue, queue.length - start, back+ 1);
            }
            front = newQueue.length - 1;
            back = queue.length - 2;
            queue = newQueue;
        }
        back = (back + 1) % queue.length;
        queue[back] = theElement;
    }
    public T remove(){
        if(isEmpty())return null;
        front = (front + 1) % queue.length;
        T frontElement = queue[front];
        queue[front] = null;
        return frontElement;
    }
    public static void main(String[] args){
        int x;
        ArrayQueue<Integer> q = new ArrayQueue<>();

        q.put(new Integer(1));
        q.put(new Integer(2));
        q.put(new Integer(3));
        q.put(new Integer(4));

        while(!q.isEmpty()){
            System.out.println("Front element is " + q.getFrontElement());
            System.out.println("Removed the element " + q.remove());
        }
    }
}
