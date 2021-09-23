import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {

	private BinaryTreeNode root;
	private int size = 0;

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
		
	BinaryTree(T[] inputArray) {
		if(inputArray == null) return;
		if(inputArray.length == 0) return;
		root = new BinaryTreeNode(inputArray[0]);
		size = 1;
		for(int i = 1; i < inputArray.length; i++) {
			add(inputArray[i]);
			size++;
		}
	}
	
	BinaryTree(ArrayList<T> inputArray) {
		if(inputArray == null) return;
		if(inputArray.size() == 0) return;
		size = 1;
		root = new BinaryTreeNode(inputArray.get(0));
		for(int i = 1; i < inputArray.size(); i++) {
			add(inputArray.get(i));
			size++;
		}
	}
	
/********************************************************************************************************
										PUBLIC METHODS
********************************************************************************************************/
	
	public T LCA(T val1, T val2) {
		ArrayList<T> pathOne = new ArrayList<T>();
		ArrayList<T> pathTwo = new ArrayList<T>();
		BinaryTreeNode a = search(root, val1, pathOne);
		BinaryTreeNode b = search(root, val2, pathTwo);
		
		if(a == null) {
			System.out.println(val1 + " does not exist in the tree!");
			return null;
		}
		if(b == null) {
			System.out.println(val2 + " does not exist in the tree!");
			return null;
		}
		
		for(int i = 0; i < pathOne.size(); i++) 
			if(!pathTwo.contains(pathOne.get(i))) 
				return pathOne.get(i - 1);
		
		return null;
	}
	
	public void printTree() 
	{
		System.out.println(print(root, ""));
	}
	
	public int size() {
		return size;
	}
	
/********************************************************************************************************
										PRIVATE METHODS
********************************************************************************************************/
	
	private void add(T value) {
		BinaryTreeNode node = root;
		while(true) {
			int comparison = node.value.compareTo(value);
			
			if(comparison == 0) 
			{
				System.out.println("Duplicate value: " + value);
				return;
			}
			
			if(comparison < 0) 
			{
				if(node.left == null) {
					node.left = new BinaryTreeNode(value);
					return;
				}
				else node = node.left;
			}
			else {
				if(node.right == null) {
					node.right = new BinaryTreeNode(value);
					return;
				}
				else node = node.right;
			}
		}
	}
	
	private BinaryTreeNode search(BinaryTreeNode node, T value, ArrayList<T> path) {
		if(node == null) return null;
		if(path != null) path.add(node.value);
		
		switch(sign(node.value.compareTo(value)))
		{
			case  0: return node;
			case  1: return search(node.right, value, path);
			case -1: return search(node.left, value, path);
			default: return null;
		}
	}
	
	private int sign(int num)
	{
		if(num > 0) return 1;
		if(num < 0) return -1;
		return 0;
	}
	
	private String print(BinaryTreeNode node, String prefix)
	{
		if(node == null) return prefix + "-null\n";
		return prefix + "-" + node.value + "\n" + print(node.left, prefix + " |") + print(node.right, prefix + "  ") ;
	}
}
