import java.util.*;

class DataDict<K, E>
{
    K key;
    E element;

    DataDict( )
    {
        key = null;
        element = null;
    }

    DataDict( K theKey, E theElement )
    {
        key = theKey;
        element = theElement;
    }

    public String toString( )
    {
        return "[" + Objects.toString( element ) +
                ", key=" + Objects.toString( key ) + "]";
    }
}

