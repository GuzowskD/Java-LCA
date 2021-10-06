import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LCATest
{
	@Test
	public void test_constructor() {
		new BinaryTree<String>((String[]) null);
		new BinaryTree<String>(new String[] {});
		new BinaryTree<Integer>(new Integer[] {1,2,3,4,5});
		
		new BinaryTree<String>((ArrayList<String>) null);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) arr.add(i);
		new BinaryTree<Integer>(arr);
	}
	
	@Test
	public void test_size() {
		
		BinaryTree<Integer> bt = new BinaryTree<Integer>((Integer[]) null);
		assertEquals("Size when null is passed as input", 0, bt.size());
		
		bt = new BinaryTree<Integer>(new Integer[] {});
		assertEquals("Size when empty array is passed as input", 0, bt.size());
		
		bt = new BinaryTree<Integer>(new Integer[] {1, 1});
		assertEquals("Size when array with duplicate values is passed as input", 1, bt.size());
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) arr.add(i);
		bt = new BinaryTree<Integer>(arr);
		assertEquals("Size when arraylist is passed as input", arr.size(), bt.size());
		
		arr = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) arr.add(i);
		arr.add(5); arr.add(4); arr.add(1);
		bt = new BinaryTree<Integer>(arr);
		assertEquals("Size when arraylist with duplicates is passed as input", 10, bt.size());
	}
	
	@Test
	public void test_treeShape() {
		String shape1 = "-5\n"
					+ " |-7\n"
					+ " | |-23\n"
					+ " | | |-74\n"
					+ " | | | |-845\n"
					+ " | | | | |-null\n"
					+ " | | | |  -98\n"
					+ " | | | |   |-102\n"
					+ " | | | |   | |-null\n"
					+ " | | | |   |  -null\n"
					+ " | | | |    -81\n"
					+ " | | | |     |-null\n"
					+ " | | | |      -null\n"
					+ " | | |  -42\n"
					+ " | | |   |-68\n"
					+ " | | |   | |-73\n"
					+ " | | |   | | |-null\n"
					+ " | | |   | |  -null\n"
					+ " | | |   |  -62\n"
					+ " | | |   |   |-null\n"
					+ " | | |   |    -null\n"
					+ " | | |    -25\n"
					+ " | | |     |-null\n"
					+ " | | |      -null\n"
					+ " | |  -10\n"
					+ " | |   |-null\n"
					+ " | |    -null\n"
					+ " |  -null\n"
					+ "  -2\n"
					+ "   |-4\n"
					+ "   | |-null\n"
					+ "   |  -null\n"
					+ "    --14\n"
					+ "     |--6\n"
					+ "     | |--1\n"
					+ "     | | |-0\n"
					+ "     | | | |-null\n"
					+ "     | | |  -null\n"
					+ "     | |  -null\n"
					+ "     |  -null\n"
					+ "      --97\n"
					+ "       |-null\n"
					+ "        -null\n";
		Integer[] arr = {5, 2, 7, 23, 74, -14, 845, -97, 42, 4, 68, -6, 73, 98, 10, 102, 25, -1, 0, 62, 81};
		BinaryTree<Integer> bt = new BinaryTree<Integer>(arr);
		assertEquals("tree shape no duplicates", shape1, bt.shape());
		
		arr = new Integer[]{5, 2, 7, 23, 74, -14, 845, -97, 42, 4, 7, 68, -6, 73, -14, 98, 10, 102, 25, -1, 0, 62, 81, 2};
		bt = new BinaryTree<Integer>(arr);
		assertEquals("tree shape with duplicates", shape1, bt.shape());
		
		bt = new BinaryTree<Integer>((Integer[]) null);
		assertEquals("tree shape with null input", "-null\n", bt.shape());
		bt = new BinaryTree<Integer>(new Integer[]{1, 2, 3, null});
		assertEquals("tree shape with one of the values being null", 
				"-1\n |-2\n | |-3\n | | |-null\n | |  -null\n |  -null\n  -null\n",
				bt.shape());
		
	}
	
	@Test
	public void test_LCA() {
		Integer[] arr = {5, 2, 7, 23, 74, -14, 845, -97, 42, 4, 68, -6, 73, 98, 10, 102, 25, -1, 0, 62, 81};
		BinaryTree<Integer> bt = new BinaryTree<Integer>(arr);
		assertEquals("random values from tree: LCA(42, 0) -> 5", (Integer) 5, bt.LCA(42, 0));
		assertEquals("same value: LCA(74, 74) -> 74", (Integer) 74, bt.LCA(74, 74));
		assertEquals("adjacent values: LCA(5, 7) -> 5", (Integer) 5, bt.LCA(5, 7));
		assertEquals("value thats not in the tree: LCA(1000, -6) -> null", (Integer) null, bt.LCA(1000, -6));
		assertEquals("null as parameter: LCA(null, 23) -> null", (Integer) null, bt.LCA(null, 23));
	}
}
