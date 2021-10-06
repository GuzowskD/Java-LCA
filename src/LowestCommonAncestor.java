import java.util.Scanner;

public class LowestCommonAncestor 
{
	
	public static final String filepath = "src/../dep/input.txt";
	
	public static void main(String[] args) 
	{

		Scanner input = new Scanner(System.in);
		System.out.println("Enter the location of your input file (or press Enter to use the default /dep/input.txt file. Eclipse only).");
		String filename = input.nextLine();
		ReadFile file = new ReadFile(filename.length() > 0 ? filename : filepath);
		BinaryTree<String> bt = new BinaryTree<String>(file.read());
		if(bt.size() == 0) 
		{
			System.err.println("Tree could not be constructed. Program exited.");
			input.close();
			return;
		}
		
		System.out.println("Your tree:");
		System.out.println(bt.shape());
		String userCommand = "";
		System.out.println("Type 'exit' to close the program at any time.");
		while(!userCommand.equalsIgnoreCase("exit")) 
		{
			System.out.print("Enter your 2 values, separated by a colon ':' > ");
			userCommand = input.nextLine();
			if(!userCommand.equalsIgnoreCase("exit")) 
			{
				String[] values = userCommand.replaceAll(" ", "").split(":");
				if(values.length == 2) 
				{
					System.out.println("LCA(" + values[0] + ", " + values[1] + ") = " + bt.LCA(values[0], values[1]) + ".\n");
				}
				else System.err.println("Error: Expected 2 values separated by a colon, received " + (values.length == 1 ? (values[0].length() == 0 ? 0 : 1) : values.length) + ".");
			}
		}
		input.close();
		System.out.println("Program finished.");
	}
}
