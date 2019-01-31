import java.util.*;

public class HashDict<K, E>{

    protected int divisor;
    protected DataDict<K, E>[] table;
    protected boolean[] neverUsed;
    protected int neverUsedSize;
    protected int size;

    public HashDict( int theDivisor ){
        if(theDivisor < 1)
            throw new IllegalArgumentException("Capacity of HashDict must be >= 1");
        divisor = theDivisor;
        table = new DataDict[ divisor ];
        neverUsed = new boolean[ divisor ];
        Arrays.fill( neverUsed, true );
        size = 0;
        neverUsedSize = 0;
    }

    public HashDict(){
        this(10);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public int probe(int i){
        return i * i;
    }

    private int search( K theKey ){
        int hash = Math.abs( theKey.hashCode( ) ) % divisor;
        int i = 0;
        int pos;
        pos = ( hash + probe(i) ) % divisor;
        do
        {
            if( neverUsed[ pos ] || ( table[ pos ] != null && table[ pos ].key.equals( theKey ) ) )
                return pos;
            i++;
            pos = ( hash + probe(i) ) % divisor;
        } while( pos != hash );
        return pos;
    }

    public E get( K theKey ){
        int b = search( theKey );
        if( neverUsed[ b ] || !table[ b ].key.equals( theKey ) )
            return null;
        return table[ b ].element;
    }

    public E put( K theKey, E theElement ){
        if(2 * (neverUsedSize + 1) > divisor)
            rehash();
        int b = search( theKey );
        if( neverUsed[ b ] ) {
            table[ b ] = new DataDict<K, E>( theKey, theElement );
            neverUsed[ b ] = false;
            neverUsedSize++;
            size++;
            return null;
        }
        else{
            if( table[ b ].key.equals( theKey ) ){
                E elementToReturn = table[ b ].element;
                table[ b ].element = theElement;
                return elementToReturn;
            }
            else throw new IllegalArgumentException( "insert failed" );
        }
    }

    private void rehash(){
        DataDict<K, E> old[] = table;
        divisor = divisor * 2;
        table = new DataDict[ divisor ];
        neverUsed = new boolean[ divisor ];
        Arrays.fill( neverUsed, true );
        size = 0;
        neverUsedSize = 0;
        for(int i = 0; i < divisor/2; i++){
            if(old[i] != null){
                put(old[i].key, old[i].element);
            }
        }
    }

    public E remove( K theKey ){
        int b = search( theKey );
        if( neverUsed[ b ] )
            return null;
        if( table[ b ].key.equals( theKey ) ){
            E elementToReturn = table[ b ].element;
            table[ b ] = null;
            size--;
            return elementToReturn;
        }
        else
            return null;
    }

    public String toString(){
        StringBuilder s = new StringBuilder( "\n[" );
        for( int i = 0; i < divisor; i++)
            s.append( "{" + Objects.toString( table[ i ] ) +
                    "," + ( neverUsed[ i ] ? "T" : "F" ) + "}, " );
        if( size > 0 )
            s.setLength( s.length( ) - 2 ); // remove last ", "
        s.append( "]\n" );
        return new String( s );
    }

    public static void main( String[] args ){
        HashDict<Integer, Integer> h = new HashDict<>( 7 );
        h.put( 80, 180 ); h.put( 40, 140 ); h.put( 65, 165 );
        System.out.println( h );
        h.put( 58, 158 ); h.put( 80, 124 ); h.put( 2, 102 );
        h.put( 7, 107 ); h.put( 65, 121);
        System.out.println( h );
        System.out.println( "Element " + h.get( 2 ) + " found" );
        System.out.println( "Element " + h.get( 58 ) + " found" );
        System.out.println( "Element " + h.remove( 58 ) + " removed" );
        System.out.println( h );
        System.out.println( "Element " + h.get( 2 ) + " found" );
        System.out.println( "Element " + h.get( 58 ) + " found" );
    }
}
