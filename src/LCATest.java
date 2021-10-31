import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LCATest
{
	@Test
	public void test_constructor_bt() {
		new BinaryTree<String>((String[]) null);
		new BinaryTree<String>(new String[] {});
		new BinaryTree<Integer>(new Integer[] {1,2,3,4,5});
		
		new BinaryTree<String>((ArrayList<String>) null);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) arr.add(i);
		new BinaryTree<Integer>(arr);
	}
	
	@Test
	public void test_size_bt() {
		
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
	public void test_treeShape_bt() {
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
	public void test_LCA_bt() {
		Integer[] arr = {5, 2, 7, 23, 74, -14, 845, -97, 42, 4, 68, -6, 73, 98, 10, 102, 25, -1, 0, 62, 81};
		BinaryTree<Integer> bt = new BinaryTree<Integer>(arr);
		assertEquals("random values from tree: LCA(42, 0) -> 5", (Integer) 5, bt.LCA(42, 0));
		assertEquals("same value: LCA(74, 74) -> 74", (Integer) 74, bt.LCA(74, 74));
		assertEquals("adjacent values: LCA(5, 7) -> 5", (Integer) 5, bt.LCA(5, 7));
		assertEquals("value thats not in the tree: LCA(1000, -6) -> null", (Integer) null, bt.LCA(1000, -6));
		assertEquals("null as parameter: LCA(null, 23) -> null", (Integer) null, bt.LCA(null, 23));
	}
	
	@Test
	public void test_contructor_dag() {
		// DAG requires a 2D array of the form {{from, to}, {from, to}, ...} where from != to.
		new DirectedAcyclicGraph<>(null);
		new DirectedAcyclicGraph<Integer>(new Integer[][] {{}});
		new DirectedAcyclicGraph<String>(new String[][] {{"a", "b"},{"b", "c"},{"a", "c"}});
		new DirectedAcyclicGraph<String>(new String[][] {{"a", "b"},{"b", "c"},{"c", "a"}});
		new DirectedAcyclicGraph<String>(new String[][] {{"a", "b"},{"b", "c"},{"c", "d", "e"}});
		new DirectedAcyclicGraph<Integer>(new Integer[][] {{1, 2}, {2, 3}, {3, 3}});
	}
	
	@Test
	public void test_size_dag() {
		assertEquals("Size when null passed to constructor", 0, new DirectedAcyclicGraph<>(null).size());
		assertEquals("Size when empty array passed to constructor", 0, new DirectedAcyclicGraph<Integer>(new Integer[][] {{}}).size());
		assertEquals("Size when proper DAG passed to constructor", 3, new DirectedAcyclicGraph<String>(new String[][] {{"a", "b"},{"b", "c"},{"a", "c"}}).size());
		assertEquals("Size when cyclic graph passed to constructor", 0, new DirectedAcyclicGraph<String>(new String[][] {{"a", "b"},{"b", "c"},{"c", "a"}}).size());
		assertEquals("Size when invalid graph format passed to constructor", 0, new DirectedAcyclicGraph<String>(new String[][] {{"a", "b"},{"b", "c"},{"c", "d", "e"}}).size());
		assertEquals("Size when invalid graph format passed to constructor", 0, new DirectedAcyclicGraph<String>(new String[][] {{"a", "b"},{"b", "c"},{"c"}}).size());
		assertEquals("Size when graph with self pointing vertex passed to constructor", 0, new DirectedAcyclicGraph<Integer>(new Integer[][] {{1, 2}, {2, 3}, {3, 3}}).size());
	}
	
	@Test
	public void test_LCA_dag() {
		Integer[][] edges1 = {
				{},
				{},
				{},
		};
		assertEquals("LCA when empty array given", new HashSet<Integer>(), new DirectedAcyclicGraph<Integer>(edges1).LCA(1, 2));
		String[][] edges2 = {
			{"a", "b"},
			{"b", "c"},
			{"a", "d"},
			{"b", "d"}
		};
		HashSet<String> res2 = new HashSet<String>();
		res2.add("b");
		assertEquals("LCA with a single result", res2, new DirectedAcyclicGraph<String>(edges2).LCA("d", "c"));
		assertEquals("LCA symmetry", new DirectedAcyclicGraph<String>(edges2).LCA("c", "d"), new DirectedAcyclicGraph<String>(edges2).LCA("d", "c"));
		Integer[][] edges3 = {
				{1, 2},
				{2, 3},
				{1, 3},
				{4, 5},
				{4, 6},
				{5, 6},
		};
		assertEquals("LCA with disjoint graph", new HashSet<Integer>(), new DirectedAcyclicGraph<Integer>(edges3).LCA(5, 2));
		Integer[][] edges4 = {
				{1, 3},	
				{1, 4},	
				{1, 7},
				{2, 3},	
				{2, 4},	
				{2, 5},	
				{2, 6},	
		};
		HashSet<Integer> res4 = new HashSet<Integer>();
		res4.add(1);
		res4.add(2);
		assertEquals("LCA with 2 roots (both matching)", res4, new DirectedAcyclicGraph<Integer>(edges4).LCA(3, 4));
		res4 = new HashSet<Integer>();
		res4.add(2);
		assertEquals("LCA with 2 roots", res4, new DirectedAcyclicGraph<Integer>(edges4).LCA(4, 6));
		assertEquals("LCA with 2 roots", res4, new DirectedAcyclicGraph<Integer>(edges4).LCA(5, 6));
		assertEquals("LCA with 2 roots and no common ancestor", new HashSet<Integer>(), new DirectedAcyclicGraph<Integer>(edges4).LCA(7, 6));
		Integer[][] edges5 = {
				{1, 2},
				{2, 3},
				{3, 4},
				{1, 5},
				{2, 5},
				{3, 5},
				{4, 5},
				{1, 6},
				{2, 6},
				{3, 6},
				{4, 6},
		};
		res4 = new HashSet<Integer>();
		res4.add(4);
		assertEquals("LCA with multiple connections of same length but only deepest wanted", res4, new DirectedAcyclicGraph<Integer>(edges5).LCA(5, 6));
		String[][] edges6 = {
				{"A", "B"},	
				{"B", "C"},	
				{"C", "D"},	
				{"D", "E"},	
				{"E", "A"},	
				{"C", "E"},	
		};
		assertEquals("LCA when a cyclic graph passed", new HashSet<String>(), new DirectedAcyclicGraph<String>(edges6).LCA("C", "A"));
	}
}
