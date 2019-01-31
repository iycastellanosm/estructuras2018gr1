public class AVLtree<T extends Comparable<? super T>> implements SetSorted<T>{

    private AVLnode<T> root;
    private int size;
    
    public AVLtree(){
        root = null;
        size = 0;
    }

    public boolean isEmpty(){
        return root == null;
    }
    
    public int size() {
    	return size;
    }
    
    public boolean contains(T x){
    	AVLnode<T> currNode = root;
        while(currNode != null){
            int comparation = x.compareTo(currNode.getElement());
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
    
    public void add( T x ){
        root = add(root, x);
    }

    private AVLnode<T> add(AVLnode<T> nodo, T x){
        if(nodo == null){
        	size++;
            return new AVLnode<T>(x);
        }
        int comparation = x.compareTo(nodo.getElement());
        if(comparation < 0 ){
            nodo.setLeftChild(add(nodo.getLeftChild(), x));
        }
        else if(comparation > 0){
            nodo.setRightChild(add(nodo.getRightChild(), x));
        }
        return balance(nodo);
    }

    public void remove( T x ){
        root = remove(root, x);   
    }

    private AVLnode<T> remove(AVLnode<T> node, T x){
        if(node == null) return null;
        int comparation = x.compareTo(node.getElement());
        if( comparation < 0)
            node.setLeftChild(remove(node.getLeftChild(), x));
        else if(comparation > 0)
            node.setRightChild(remove(node.getRightChild(), x));
        else{
            if(node.getLeftChild() != null && node.getRightChild() != null){
                node.setElement(getMin(node.getRightChild()));
                node.setRightChild(remove(node.getRightChild(), node.getElement()));
            }
            else {
            	node = (node.getLeftChild() != null) ? node.getLeftChild() : node.getRightChild();
            	size--;
            }
        }
        return balance(node);
    }
    
    public T getMax(){
        return getMax(root);
    }

    public T getMax(AVLnode<T> nodoInicial){
        AVLnode<T> nodo = nodoInicial;
        T elemento = null;
        while(nodo != null){
            elemento = nodo.getElement();
            nodo = nodo.getRightChild();
        }
        return elemento;
    }
    
    public T getMin(){
        return getMin(root);
    }

    public T getMin(AVLnode<T> nodoInicial){
        AVLnode<T> nodo = nodoInicial;
        T elemento = null;
        while(nodo != null){
            elemento = nodo.getElement();
            nodo = nodo.getLeftChild();
        }
        return elemento;
    }
    
    public int height(){
        return height(root);
    }
    
    private int height( AVLnode<T> nodo ){
        return nodo == null ? 0 : nodo.getHeight();
    }

    private AVLnode<T> rotateWithLeftChild( AVLnode<T> b ){
        AVLnode<T> a = b.getLeftChild();
        b.setLeftChild(a.getRightChild());
        a.setRightChild(b);
        b.setHeight(Math.max( height( b.getLeftChild() ), height( b.getRightChild() ) ) + 1);
        a.setHeight(Math.max( height( a.getLeftChild() ), b.getHeight() ) + 1);
        return a;
    }

    private AVLnode<T> rotateWithRightChild( AVLnode<T> b ){
        AVLnode<T> a = b.getRightChild();
        b.setRightChild(a.getLeftChild());
        a.setLeftChild(b);
        b.setHeight(Math.max( height( b.getLeftChild() ), height( b.getRightChild() ) ) + 1);
        a.setHeight(Math.max( b.getHeight(), height( a.getRightChild() ) ) + 1);
        return a;
    }

    private AVLnode<T> doubleWithLeftChild( AVLnode<T> a ){
        a.setLeftChild(rotateWithRightChild( a.getLeftChild() ));
        return rotateWithLeftChild( a );
    }

    private AVLnode<T> doubleWithRightChild( AVLnode<T> a ){
        a.setRightChild(rotateWithLeftChild( a.getRightChild() ));
        return rotateWithRightChild( a );
    }

    private AVLnode<T> balance( AVLnode<T> nodo ){
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
    	AVLtree<String> usuarios = new AVLtree<>();
        System.out.println("Empty: " + usuarios.isEmpty() + " Size: " + usuarios.size());
        usuarios.add("hi");
        usuarios.add("this");
        usuarios.add("is");
        usuarios.add("a");
        usuarios.add("test");
        System.out.println("Empty: " + usuarios.isEmpty() + " Size: " + usuarios.size()); 
        System.out.println("hello " + usuarios.contains("hello"));
        System.out.println("a " + usuarios.contains("a"));
        System.out.println("test " + usuarios.contains("test"));
        System.out.println("First: " + usuarios.getMin() + " Last: " + usuarios.getMax());
        System.out.println("height: " + usuarios.height());
        usuarios.remove("is");
        usuarios.remove("a");
        usuarios.remove("remove");
        System.out.println("Empty: " + usuarios.isEmpty() + " Size: " + usuarios.size());
        System.out.println("hi " + usuarios.contains("hello"));
        System.out.println("a " + usuarios.contains("a"));
        System.out.println("test " + usuarios.contains("test"));
        System.out.println("First: " + usuarios.getMin() + " Last: " + usuarios.getMax());
        System.out.println("height: " + usuarios.height());	
    }
}

