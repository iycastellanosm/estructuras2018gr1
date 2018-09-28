public interface BinaryTree <T>{
    boolean isEmpty();
    T root();
    void makeTree(T element, BinaryTree<T> left, BinaryTree<T> right);
    BinaryTree<T> removeLeftSubtree();
    BinaryTree<T> removeRightSubtree();
    void preOrder();
    void inOrder();
    void postOrder();
    void levelOrder();
}
