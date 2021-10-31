import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class DirectedAcyclicGraph<T extends Comparable<T>> {
	private HashMap<T, TreeSet<T>> graph;
	private ArrayList<T> roots;
	private int size;
	@SuppressWarnings("unchecked")
	public DirectedAcyclicGraph(T[][] graphArray) {
		roots = new ArrayList<T>();
		graph = new HashMap<T, TreeSet<T>>();
		if(graphArray == null) return;
		HashMap<T, Integer> rootCounter = new HashMap<T, Integer>();
		ArrayList<T> elements = new ArrayList<T>();
		for(int i = 0; i < graphArray.length; i++)
		{
			if(graphArray[i].length != 2) {
				System.err.println("Invalid graph. Expected edge size of 2, got " + graphArray[i].length);
				graph = new HashMap<T, TreeSet<T>>();
				return;
			}
			
			if(graphArray[i][0].equals(graphArray[i][1])) {
				System.err.println("Invalid graph. A vertex cannot connect to itself.");
				graph = new HashMap<T, TreeSet<T>>();
				return;
			}
			
			if(!elements.contains(graphArray[i][0])) {
				elements.add(graphArray[i][0]);
			} else if(!elements.contains(graphArray[i][1])) {
				elements.add(graphArray[i][1]);
			}
			
			if(!rootCounter.containsKey(graphArray[i][0])) {
				rootCounter.put(graphArray[i][0], 0);
			} 
			if(!rootCounter.containsKey(graphArray[i][1])) {
				rootCounter.put(graphArray[i][1], 0);
			}
			
			if(!graph.containsKey(graphArray[i][0])) {
				graph.put(graphArray[i][0], new TreeSet<T>());
			}
			
			rootCounter.put(graphArray[i][1], rootCounter.get(graphArray[i][1]) + 1);
			
			graph.get(graphArray[i][0]).add(graphArray[i][1]);
			
			if(!graph.containsKey(graphArray[i][1])) 
				graph.put(graphArray[i][1], new TreeSet<T>());
		}
		
		for(T key : rootCounter.keySet()) {
			if(rootCounter.get(key) == 0) {
				roots.add(key);
			}
		}
		
		if(roots.size() == 0) {
			roots.add((T) graph.keySet().toArray()[0]);
		}
		
		size = elements.size();
		if(containsCycles()) {
			graph = new HashMap<T, TreeSet<T>>();
			size = 0;
			roots = new ArrayList<T>();
			System.err.println("Error: Graph contains cycles.");
		}
	}
	
	private boolean containsCycles() {
		for(ArrayList<T> path : getAllPaths()) {
			if(new HashSet<T>(path).size() != path.size()) {
				System.out.println(path);
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<ArrayList<T>> getAllPaths() {
		ArrayList<ArrayList<T>> paths = new ArrayList<ArrayList<T>>();
		for(T root : roots) {
			getAllPaths(new ArrayList<T>(), root, paths);
		}
		return paths;
	}

	private void getAllPaths(ArrayList<T> currentPath, T nextNode, ArrayList<ArrayList<T>> cycles) {
		if(currentPath.contains(nextNode)) {
			currentPath.add(nextNode);
			cycles.add(new ArrayList<T>(currentPath));
			currentPath.remove(nextNode);
			return;
		}
		currentPath.add(nextNode);
		if(graph.containsKey(nextNode))
		for(T key : graph.get(nextNode)) {
			getAllPaths(currentPath, key, cycles);
		}
		cycles.add(new ArrayList<T>(currentPath));
		currentPath.remove(nextNode);
	}
	
	public ArrayList<ArrayList<T>> getPathsTo(T val) {
		ArrayList<ArrayList<T>> paths = getAllPaths();
		ArrayList<ArrayList<T>> resPaths = new ArrayList<ArrayList<T>>();
		for(ArrayList<T> path : paths) {
			if(path.get(path.size() - 1).equals(val)) {
				resPaths.add(new ArrayList<T>(path));
			}
		}
		return resPaths;
	}
	
	public HashSet<T> LCA(T a, T b) {
		ArrayList<ArrayList<T>> aPaths = getPaths(a);
		ArrayList<ArrayList<T>> bPaths = getPaths(b);
		HashSet<T> aUnion = new HashSet<T>();
		HashSet<T> bUnion = new HashSet<T>();
		for(ArrayList<T> list : aPaths) {
			aUnion.addAll(list);
		}
		
		for(ArrayList<T> list : bPaths) {
			bUnion.addAll(list);
		}
		aUnion.retainAll(bUnion);
		HashSet<T> res = new HashSet<T>();
		for(T t : aUnion) {
			boolean check = true;
			for(T val : graph.get(t)) {
				if(aUnion.contains(val)) {
					check = false;
					break;
				}
			}
			if(check) res.add(t);
		}
		return res;
	}
	
	public int size() {
		return size;
	}
	
	private ArrayList<ArrayList<T>> getPaths(T val) {
		ArrayList<ArrayList<T>> res = new ArrayList<ArrayList<T>>();
		for(T root : roots) {
			getPaths(new ArrayList<T>(), root, val,  res);
		}
		return res;
	}
	
	private void getPaths(ArrayList<T> path, T vertex, T target, ArrayList<ArrayList<T>> paths) {
		path.add(vertex);
		
		if(vertex.equals(target)) {
			paths.add(new ArrayList<T>(path));
			path.remove(path.size() - 1);
			return;
		}
		
		for(T v : graph.get(vertex)) {
			getPaths(path, v, target, paths);
		}
		
		path.remove(path.size() - 1);
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("DirectedAcyclicGraph {\n");
		for(T key : graph.keySet()) {
			res.append("   " + key + " -> " + graph.get(key) + "\n");
		}
		if(graph.size() == 0) res.append("\n");
		res.append("}");
		return res.toString();
	}
}
