public class BinaryTreeNode<T> {
    T element;
    BinaryTreeNode<T> leftChild;
    BinaryTreeNode<T> rightChild;
    
    public BinaryTreeNode(){}
    
    public BinaryTreeNode(T theElement){
        element = theElement;
        leftChild = null;
        rightChild = null;
    }
    
    public BinaryTreeNode(T theElement, BinaryTreeNode<T> theLeftChild, BinaryTreeNode<T> theRightChild){
        element = theElement;
        leftChild = theLeftChild;
        rightChild = theRightChild;
    }
    
    public BinaryTreeNode<T> getLeftChild(){
        return leftChild;
    }
    
    public BinaryTreeNode<T> getRightChild(){
        return rightChild;
    }
    
    public T getElement(){
        return element;
    }
    
    public void setLeftChild(BinaryTreeNode<T> theLeftChild){
        leftChild = theLeftChild;
    }
    
    public void setRightChild(BinaryTreeNode<T> theRightChild){
        rightChild = theRightChild;
    }
    
    public void setElement(T theElement){
        element = theElement;
    }
    @Override
    
    public String toString(){
        return element.toString();
    }
}
