/**
 * Created by ivanc on 13/08/2017.
 */
import java.util.*;

public class ArrayLinearListIterator<T> implements Iterator<T> {
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
