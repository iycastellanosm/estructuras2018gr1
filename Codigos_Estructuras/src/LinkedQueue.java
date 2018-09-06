public class LinkedQueue<T> implements Queue<T>{
    protected ChainNode<T> front;
    protected ChainNode<T> back;

    public LinkedQueue(){
        front = back = null;
    }
    public boolean isEmpty(){
        return front == null;
    }
    public T getFrontElement(){
        return isEmpty() ? null : front.element;
    }
    public void put(T theElement){
        ChainNode<T> p = new ChainNode<T>(theElement, null);
        if(front == null) front = p;
        else back.next = p;
        back = p;
    }
    public T remove(){
        if(isEmpty()) return null;
        T frontElement = front.element;
        front = front.next;
        if(isEmpty()) back = null;
        return frontElement;
    }
    public static void main(String[] args){
        int x;
        LinkedQueue<Integer> q = new LinkedQueue<>();

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
