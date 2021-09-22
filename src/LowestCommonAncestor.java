public class LowestCommonAncestor {

	public static void main(String[] args) {
		ReadFile file = new ReadFile("src/../dep/input.txt");
		String[] strings = file.read();
		for(String s : strings) {
			System.out.println(s);
		}
	}
}
