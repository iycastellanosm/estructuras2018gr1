public class AVLnode<T> {
    T element;
    AVLnode<T> leftChild, rightChild;
    int height;

    public AVLnode(){
        this(null, null, null);
    }

    public AVLnode(T element){
        this(element, null,null);
    }

    public AVLnode(T element, AVLnode<T> leftChild, AVLnode<T> rightChild){
    	this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.height = 1;
    }

    public AVLnode<T> getLeftChild(){
        return leftChild;
    }

    public AVLnode<T> getRightChild(){
        return rightChild;
    }

    public T getElement(){
        return element;
    }

    public int getHeight(){
        return height;
    }

    public void setLeftChild(AVLnode<T> leftChild){
        this.leftChild = leftChild;
    }

    public void setRightChild(AVLnode<T> rightChild){
        this.rightChild = rightChild;
    }

    public void setElement(T element){
        this.element = element;
    }

    public void setHeight(int height){
        this.height = height;
    }

    @Override
    public String toString(){
        return element.toString();
    }
}
