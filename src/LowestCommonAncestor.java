
public class LowestCommonAncestor {
	
	public static final String filepath = "src/../dep/input.txt";
	
	public static void main(String[] args) {
		ReadFile<String> file = new ReadFile<String>(filepath);
		BinaryTree<String> bt = new BinaryTree<String>(file.read());
		bt.printTree();
	}
}
