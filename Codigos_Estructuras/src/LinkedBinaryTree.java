public class LinkedBinaryTree<T> implements BinaryTree<T>{
	BinaryTreeNode<T> root;
       
    public LinkedBinaryTree(){
        root = null;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public T root(){
        return (root == null)? null: root.element;
    }
    
    public void makeTree(T element, BinaryTree<T> left, BinaryTree<T> right){
        this.root = new BinaryTreeNode<T>(element, ((LinkedBinaryTree<T>) left).root,
                ((LinkedBinaryTree<T>) right).root);
    }
    
    public BinaryTree<T> removeLeftSubtree(){
        if(root == null) throw new IllegalArgumentException("tree is empty");
        LinkedBinaryTree<T> leftSubtree = new LinkedBinaryTree<T>();
        leftSubtree.root = root.leftChild;
        root.leftChild = null;
        return (BinaryTree<T>) leftSubtree;
    }
    
    public BinaryTree<T> removeRightSubtree(){
        if(root == null)  throw new IllegalArgumentException("tree is empty");
        LinkedBinaryTree<T> rightSubtree = new LinkedBinaryTree<T>();
        rightSubtree.root = root.rightChild;
        root.rightChild = null;
        return (BinaryTree<T>) rightSubtree;
    }
    
    public void preOrder(){
        thePreOrder(root);
    }
    
    static <T> void thePreOrder(BinaryTreeNode<T> t){
        if(t != null){
            System.out.print(t + " ");
            thePreOrder(t.leftChild);
            thePreOrder(t.rightChild);
        }
    }
    
    public void inOrder(){
        theInOrder(root);
    }
    
    static <T> void theInOrder(BinaryTreeNode<T> t){
        if(t != null){
            theInOrder(t.leftChild);
            System.out.print(t + " ");
            theInOrder(t.rightChild);
        }
    }
    
    public void postOrder(){
        thePostOrder(root);
    }
    
    static <T> void thePostOrder(BinaryTreeNode<T> t){
        if(t != null){
            thePostOrder(t.leftChild);
            thePostOrder(t.rightChild);
            System.out.print(t + " ");
        }
    }
    
    public void levelOrder(){
        LinkedQueue< BinaryTreeNode<T> > q = new LinkedQueue<>();
        q.put(root);
        while(!q.isEmpty()){
        	BinaryTreeNode<T> t = (BinaryTreeNode<T>) q.remove();
        	System.out.print(t + " ");
            if(t.leftChild != null)
            	q.put(t.leftChild);
            if(t.rightChild != null)
            	q.put(t.rightChild);
        }
    }
    
    public void quizOrder(){
        LinkedStack< BinaryTreeNode<T> > q = new LinkedStack<>();
        q.push(root);
        while(!q.isEmpty()){
        	BinaryTreeNode<T> t = (BinaryTreeNode<T>) q.pop();
        	System.out.print(t + " ");
            if(t.leftChild != null)
            	q.push(t.leftChild);
            if(t.rightChild != null)
            	q.push(t.rightChild);
        }
    }

    public int size(){
        return theSize(root);
    }
    
    static <T> int theSize (BinaryTreeNode<T> t){
        if(t == null) return 0;
        int hl = theSize(t.leftChild);
        int hr = theSize(t.rightChild);
        return hl + hr + 1;
    }
    
    public int height(){
        return theHeight(root);
    }
    
    static <T> int theHeight (BinaryTreeNode<T> t){
        if(t == null) return 0;
        int hl = theHeight(t.leftChild);
        int hr = theHeight(t.rightChild);
        if(hl > hr) return ++hl;
        else return ++hr;
    }
    
    public static void main(String args[]){
        LinkedBinaryTree<Integer> a = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> x = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> y = new LinkedBinaryTree<>();
        LinkedBinaryTree<Integer> z = new LinkedBinaryTree<>();

        y.makeTree(new Integer(1), a, a);
        z.makeTree(new Integer(2), a, a);
        x.makeTree(new Integer(3), y, z);
        y.makeTree(new Integer(4), x, a);
        y.makeTree(new Integer(5), a, y);

        System.out.println("Preorder sequence is ");
        y.preOrder();
        System.out.println("\nInorder sequence is ");
        y.inOrder();
        System.out.println("\nPostorder sequence is ");
        y.postOrder();
        System.out.println("\nLevel order sequence is ");
        y.levelOrder();
        System.out.println("\nNumber of nodes is: " + y.size() );
        System.out.println("Height of tree is: " + y.height() ); 
    }
}
