public class MapNode<K, E> {
    K key;
    E element;
    MapNode<K, E> leftChild, rightChild;
    int height;

    public MapNode(){
        this(null, null, null, null);
    }
    
    public MapNode(K key){
        this(key, null, null, null);
    }
    
    public MapNode(K key, E element){
        this(key, element, null, null);
    }

    public MapNode(K key, E element, MapNode<K, E> leftChild, MapNode<K, E> rightChild){
    	this.key = key;
    	this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = 1;
    }

    public MapNode<K, E> getLeftChild(){
        return leftChild;
    }

    public MapNode<K, E> getRightChild(){
        return rightChild;
    }

    public E getElement(){
        return element;
    }

    public int getHeight(){
        return height;
    }

    public void setLeftChild(MapNode<K, E> leftChild){
        this.leftChild = leftChild;
    }

    public void setRightChild(MapNode<K, E> rightChild){
        this.rightChild = rightChild;
    }

    public void setElement(E element){
        this.element = element;
    }

    public void setHeight(int height){
        this.height = height;
    }
    
    public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	@Override
    public String toString(){
        return element.toString();
    }
}
