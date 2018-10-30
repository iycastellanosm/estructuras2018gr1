public class BinarySearchTree<T extends Comparable<? super T>> implements SetSorted<T> {
	
	private BinaryTreeNode<T> root;
	private int size;

    public BinarySearchTree(){
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
    	BinaryTreeNode<T> currNode = root;
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

    private BinaryTreeNode<T> add(BinaryTreeNode<T> nodo, T x){
        if(nodo == null){
        	size++;
            return new BinaryTreeNode<T>(x);
        }
        int comparation = x.compareTo(nodo.getElement());
        if(comparation < 0 ){
            nodo.setLeftChild(add(nodo.getLeftChild(), x));
        }
        else if(comparation > 0){
            nodo.setRightChild(add(nodo.getRightChild(), x));
        }
        return nodo;
    }

    public void remove(T x){
        root = remove(root, x);
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T x){
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
            else node = (node.getLeftChild() != null) ? node.getLeftChild() : node.getRightChild();
            size--;
        }
        return node;
    }

    public T getMax(){
        return getMax(root);
    }

    public T getMax(BinaryTreeNode<T> nodoInicial){
    	BinaryTreeNode<T> nodo = nodoInicial;
        T maxElement = null;
        while(nodo != null) {
        	maxElement = nodo.getElement();
        	nodo = nodo.getRightChild();
        }
        return maxElement;
    }
    
    public T getMin(){
        return getMin(root);
    }
    
    public T getMin(BinaryTreeNode<T> nodoInicial){
        BinaryTreeNode<T> nodo = nodoInicial;
        T minElement = null;
        while(nodo != null) {
        	minElement = nodo.getElement();
        	nodo = nodo.getLeftChild();
        }
        return minElement;
    }

    public static void main(String args[]){
        BinarySearchTree<String> usuarios = new BinarySearchTree<>();
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
        usuarios.remove("is");
        usuarios.remove("a");
        usuarios.remove("remove");
        System.out.println("Empty: " + usuarios.isEmpty() + " Size: " + usuarios.size());
        System.out.println("hi " + usuarios.contains("hello"));
        System.out.println("a " + usuarios.contains("a"));
        System.out.println("test " + usuarios.contains("test"));
        System.out.println("First: " + usuarios.getMin() + " Last: " + usuarios.getMax());
    }
}