import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {

	private class BinaryTreeNode {
		BinaryTreeNode(T value) {
			this.value = value;
			left = null;
			right = null;
		}
		T value;
		BinaryTreeNode left;
		BinaryTreeNode right;
	}
	
	private BinaryTreeNode root;
	
	BinaryTree(T[] inputArray) {
		if(inputArray == null) return;
		if(inputArray.length == 0) return;
		root = new BinaryTreeNode(inputArray[0]);
		for(int i = 1; i < inputArray.length; i++) {
			add(inputArray[i]);
		}
	}
	
	BinaryTree(ArrayList<T> inputArray) {
		if(inputArray == null) return;
		if(inputArray.size() == 0) return;
		root = new BinaryTreeNode(inputArray.get(0));
		for(int i = 1; i < inputArray.size(); i++) {
			add(inputArray.get(i));
		}
	}
	
	private void add(T value) {
		BinaryTreeNode node = root;
		while(true) {
			int comparison = node.value.compareTo(value);
			if(comparison < 0) 
			{
				if(node.left == null) {
					node.left = new BinaryTreeNode(value);
					break;
				}
				else node = node.left;
			}
			else {
				if(node.right == null) {
					node.right = new BinaryTreeNode(value);
					break;
				}
				else node = node.right;
			}
		}
	}
	
	
	public BinaryTreeNode search(T value) {
		return search(root, value);
	}
	
	private BinaryTreeNode search(BinaryTreeNode node, T value) {
		if(node == null) return null;
		
		switch(node.value.compareTo(value))
		{
			case  0: return node;
			case  1: return search(node.right, value);
			case -1: return search(node.left, value);
			default: return null;
		}
	}
	
	public void printTree() 
	{
		System.out.println(print(root, ""));
	}

	private String print(BinaryTreeNode node, String prefix)
	{
		if(node == null) return prefix + "-null\n";
		return prefix + "-" + node.value + "\n" + print(node.left, prefix + " |") + print(node.right, prefix + "  ") ;
	}
}
