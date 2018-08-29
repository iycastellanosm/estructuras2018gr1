import java.util.*;

public class ChainIterator<T> implements Iterator<T>{
    private ChainNode<T> nextNode;

    public ChainIterator(ChainNode<T> firstNode){
        nextNode = firstNode;
    }

    public boolean hasNext(){
        return nextNode != null;
    }

    public T next(){
        if(nextNode != null){
            T elementToReturn = nextNode.element;
            nextNode = nextNode.next;
            return elementToReturn;
        }
        else throw new NoSuchElementException("No next element");
    }

    public void remove(){
        throw new UnsupportedOperationException("remove not supported");
    }
}