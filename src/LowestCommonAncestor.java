import java.util.Scanner;

public class LowestCommonAncestor 
{
	
	public static final String filepath = "../dep/input.txt";
	
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("Type BT to use Binary Tree LCA, else type DAG: ");
		String type = input.nextLine();
		if(type.equalsIgnoreCase("BT")) {
			System.out.println("Enter the location of your input file: ");
			String filename = input.nextLine();
			System.out.println(filename);
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
		} else if(type.equalsIgnoreCase("DAG")) {
			ReadFile read = new ReadFile("../dep/dag_input.txt");
			Object[] contents = read.read().toArray();
			String[][] dagInput = new String[contents.length][2];
			for(int i = 0; i < contents.length; i++) {
				dagInput[i] = ((String)contents[i]).split(" ");
			}
			DirectedAcyclicGraph<String> dag = new DirectedAcyclicGraph<String>(dagInput);
			System.out.println(dag);
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
						System.out.println("LCA(" + values[0] + ", " + values[1] + ") = " + dag.LCA(values[0], values[1]) + ".\n");
					}
					else System.err.println("Error: Expected 2 values separated by a colon, received " + (values.length == 1 ? (values[0].length() == 0 ? 0 : 1) : values.length) + ".");
				}
			}
			} else System.out.println("Invalid input.");
		input.close();
		
		System.out.println("Program finished.");
	}
}
