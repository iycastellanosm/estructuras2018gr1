public class MapTree<K extends Comparable<? super K>, E> implements MapSorted<K, E>{

    private MapNode<K, E> root;
    private int size;
    
    public MapTree(){
        root = null;
        size = 0;
    }

    public boolean isEmpty(){
        return root == null;
    }
    
    public int size() {
    	return size;
    }
    
    public boolean containsKey(K key){
    	MapNode<K, E> currNode = root;
        while(currNode != null){
            int comparation = key.compareTo(currNode.getKey());
            if(comparation == 0) return true;
            if(comparation < 0){
                currNode = currNode.getLeftChild();
            }
            else{
                currNode = currNode.getRightChild();
            }
        }
    	return false;
    }
    
    public E get(K key){
    	MapNode<K, E> currNode = root;
    	while(currNode != null){
            int comparation = key.compareTo(currNode.getKey());
            if(comparation == 0) return currNode.getElement();
            if(comparation < 0){
                currNode = currNode.getLeftChild();
            }
            else{
                currNode = currNode.getRightChild();
            }
        }
    	return null;
    }
    
    public void put(K key, E x){
        root = put(root, key, x);
    }

    private MapNode<K, E> put(MapNode<K, E> nodo, K key, E x){
        if(nodo == null){
        	size++;
            return new MapNode<K, E>(key, x);
        }
        int comparation = key.compareTo(nodo.getKey());
        if(comparation < 0 ){
            nodo.setLeftChild(put(nodo.getLeftChild(), key, x));
        }
        else if(comparation > 0){
            nodo.setRightChild(put(nodo.getRightChild(), key, x));
        }
        else {
        	nodo.setElement(x);
        }
        return balance(nodo);
    }

    public void remove( K key ){
        root = remove(root, key);   
    }

    private MapNode<K, E> remove(MapNode<K, E> node, K key){
        if(node == null) return null;
        int comparation = key.compareTo(node.getKey());
        if( comparation < 0)
            node.setLeftChild(remove(node.getLeftChild(), key));
        else if(comparation > 0)
            node.setRightChild(remove(node.getRightChild(), key));
        else{
            if(node.getLeftChild() != null && node.getRightChild() != null){
                node.setKey(getMin(node.getRightChild()));
                node.setElement(pollMin(node.getRightChild()));
                node.setRightChild(remove(node.getRightChild(), node.getKey()));
            }
            else node = (node.getLeftChild() != null) ? node.getLeftChild() : node.getRightChild();
            size--;
        }
        return balance(node);
    }
    
    public K getMax(){
        return getMax(root);
    }

    public K getMax(MapNode<K, E> nodoInicial){
        MapNode<K, E> nodo = nodoInicial;
        K llave = null;
        while(nodo != null){
            llave = nodo.getKey();
            nodo = nodo.getRightChild();
        }
        return llave;
    }
    
    public E pollMax(){
        return pollMax(root);
    }

    public E pollMax(MapNode<K, E> nodoInicial){
        MapNode<K, E> nodo = nodoInicial;
        E elemento = null;
        while(nodo != null){
            elemento = nodo.getElement();
            nodo = nodo.getRightChild();
        }
        return elemento;
    }
    
    public K getMin(){
        return getMin(root);
    }

    public K getMin(MapNode<K, E> nodoInicial){
        MapNode<K, E> nodo = nodoInicial;
        K llave = null;
        while(nodo != null){
            llave = nodo.getKey();
            nodo = nodo.getLeftChild();
        }
        return llave;
    }
    
    public E pollMin(){
        return pollMin(root);
    }

    public E pollMin(MapNode<K, E> nodoInicial){
        MapNode<K, E> nodo = nodoInicial;
        E elemento = null;
        while(nodo != null){
            elemento = nodo.getElement();
            nodo = nodo.getLeftChild();
        }
        return elemento;
    }
    
    public int height(){
        return height(root);
    }
    
    private int height( MapNode<K, E> nodo ){
        return nodo == null ? 0 : nodo.getHeight();
    }

    private MapNode<K, E> rotateWithLeftChild( MapNode<K, E> b ){
        MapNode<K, E> a = b.getLeftChild();
        b.setLeftChild(a.getRightChild());
        a.setRightChild(b);
        b.setHeight(Math.max( height( b.getLeftChild() ), height( b.getRightChild() ) ) + 1);
        a.setHeight(Math.max( height( a.getLeftChild() ), b.getHeight() ) + 1);
        return a;
    }

    private MapNode<K, E> rotateWithRightChild( MapNode<K, E> b ){
        MapNode<K, E> a = b.getRightChild();
        b.setRightChild(a.getLeftChild());
        a.setLeftChild(b);
        b.setHeight(Math.max( height( b.getLeftChild() ), height( b.getRightChild() ) ) + 1);
        a.setHeight(Math.max( b.getHeight(), height( a.getRightChild() ) ) + 1);
        return a;
    }

    private MapNode<K, E> doubleWithLeftChild( MapNode<K, E> a ){
        a.setLeftChild(rotateWithRightChild( a.getLeftChild() ));
        return rotateWithLeftChild( a );
    }

    private MapNode<K, E> doubleWithRightChild( MapNode<K, E> a ){
        a.setRightChild(rotateWithLeftChild( a.getRightChild() ));
        return rotateWithRightChild( a );
    }

    private MapNode<K, E> balance( MapNode<K, E> nodo ){
        if( nodo == null ) return nodo;
        if( height( nodo.getLeftChild() ) - height( nodo.getRightChild() ) > 1 )
            if( height( nodo.getLeftChild().getLeftChild() ) >= height( nodo.getLeftChild().getRightChild() ) )
                nodo = rotateWithLeftChild( nodo );
            else
                nodo = doubleWithLeftChild( nodo );
        else if( height( nodo.getRightChild() ) - height( nodo.getLeftChild() ) > 1 )
            if( height( nodo.getRightChild().getRightChild() ) >= height( nodo.getRightChild().getLeftChild() ) )
                nodo = rotateWithRightChild( nodo );
            else
                nodo = doubleWithRightChild( nodo );
        nodo.setHeight(Math.max( height( nodo.getLeftChild() ), height( nodo.getRightChild() ) ) + 1);
        return nodo;
    }

    public static void main( String [] args ){
    	MapTree<Integer, String> usuarios = new MapTree<>();
        System.out.println("Empty: " + usuarios.isEmpty() + " Size: " + usuarios.size());
        usuarios.put(3, "hi");
        usuarios.put(5, "this");
        usuarios.put(4, "is");
        usuarios.put(1, "a");
        usuarios.put(6, "test");
        System.out.println("Empty: " + usuarios.isEmpty() + " Size: " + usuarios.size()); 
        System.out.println("key 7 is on map: " + usuarios.containsKey(7));
        System.out.println("key 1 is on map:  " + usuarios.containsKey(1));
        System.out.println("key 6 is:  " + usuarios.get(6));
        System.out.println("key 8 is:  " + usuarios.get(8));
        System.out.println("height: " + usuarios.height());
        usuarios.remove(4);
        usuarios.remove(1);
        usuarios.remove(10);
        usuarios.put(6, "new");
        System.out.println("Empty: " + usuarios.isEmpty() + " Size: " + usuarios.size()); 
        System.out.println("key 7 is on map: " + usuarios.containsKey(7));
        System.out.println("key 1 is on map:  " + usuarios.containsKey(1));
        System.out.println("key 6 is:  " + usuarios.get(6));
        System.out.println("key 8 is:  " + usuarios.get(8));
        System.out.println("height: " + usuarios.height());	
    }
}

