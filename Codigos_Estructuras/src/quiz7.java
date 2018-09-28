
public class quiz7 {
	public static void main(String args[]) {
        /*
		LinkedBinaryTree<String> aq = new LinkedBinaryTree<>();
        LinkedBinaryTree<String> xq = new LinkedBinaryTree<>();
        LinkedBinaryTree<String> yq = new LinkedBinaryTree<>();
        LinkedBinaryTree<String> zq = new LinkedBinaryTree<>();

        yq.makeTree(new String("h"), aq, aq);
        xq.makeTree(new String("i"), aq, aq);
        zq.makeTree(new String("e"), yq, xq);
        yq.makeTree(new String("d"), aq, aq);
        xq.makeTree(new String("b"), yq, zq);
        yq.makeTree(new String("j"), aq, aq);
        yq.makeTree(new String("g"), yq, aq);
        zq.makeTree(new String("f"), aq, aq);
        yq.makeTree(new String("c"), zq, yq);
        zq.makeTree(new String("a"), xq, yq);

        System.out.println("Level order sequence is ");
        zq.levelOrder();
        System.out.println();
        */
        
        LinkedBinaryTree<String> aq = new LinkedBinaryTree<>();
        LinkedBinaryTree<String> xq = new LinkedBinaryTree<>();
        LinkedBinaryTree<String> yq = new LinkedBinaryTree<>();
        LinkedBinaryTree<String> zq = new LinkedBinaryTree<>();

        yq.makeTree(new String("h"), aq, aq);
        xq.makeTree(new String("i"), aq, aq);
        zq.makeTree(new String("e"), yq, xq);
        yq.makeTree(new String("d"), aq, aq);
        xq.makeTree(new String("b"), yq, zq);
        yq.makeTree(new String("j"), aq, aq);
        yq.makeTree(new String("g"), yq, aq);
        zq.makeTree(new String("f"), aq, aq);
        yq.makeTree(new String("c"), zq, yq);
        zq.makeTree(new String("a"), xq, yq);

        System.out.println("quiz order sequence is ");
        zq.quizOrder();
        System.out.println();
        
	}
}
