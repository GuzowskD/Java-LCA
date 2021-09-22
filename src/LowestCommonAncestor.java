
public class LowestCommonAncestor {
	
	public static final String filepath = "src/../dep/input.txt";
	
	public static void main(String[] args) {
		ReadFile file = new ReadFile(filepath);
		BinaryTree<String> bt = new BinaryTree<String>(file.read());
		System.out.println("Your tree:");
		bt.printTree();
		System.out.println("LCA(C, J) = " + bt.LCA("C", "J") + ".");
		System.out.println("LCA(C, Q) = " + bt.LCA("C", "Q") + ".");
		System.out.println("LCA(O, X) = " + bt.LCA("O", "X") + ".");
	}
}
