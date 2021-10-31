import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;



public class DirectedAcyclicGraph<T> {
	private HashMap<T, ArrayList<T>> graph;
	private ArrayList<T> roots;
	private int size;
	
	public DirectedAcyclicGraph(T[][] graphArray) {
		roots = new ArrayList<T>();
		graph = new HashMap<T, ArrayList<T>>();
	}
	
	private boolean containsCycles() {
		return false;
	}
	
	public HashSet<T> LCA(T a, T b) {
		return new HashSet<T>();
	}
	
	public int size() {
		return 0;
	}
	
}
