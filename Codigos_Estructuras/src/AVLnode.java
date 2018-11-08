/**
 * Created by ivanc on 19/09/2017.
 */

public class AVLnode<T> {
    T element;
    AVLnode<T> leftChild;
    AVLnode<T> rightChild;
    int height;

    public AVLnode(){
        this(null, null, null);
    }

    public AVLnode(T theElement){
        this(theElement, null,null);
    }

    public AVLnode(T theElement, AVLnode<T> theLeftChild, AVLnode<T> theRightChild){
        element = theElement;
        leftChild = theLeftChild;
        rightChild = theRightChild;
        height = 0; //no ha sido definido
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

    public void setLeftChild(AVLnode<T> theLeftChild){
        leftChild = theLeftChild;
    }

    public void setRightChild(AVLnode<T> theRightChild){
        rightChild = theRightChild;
    }

    public void setElement(T theElement){
        element = theElement;
    }

    public void setHeight(int theHeight){
        height = theHeight;
    }

    @Override
    public String toString(){
        return element.toString();
    }
}
