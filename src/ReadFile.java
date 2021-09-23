import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile 
{
	private BufferedReader file;
	private FileReader fileReader;

	ReadFile(String filename)
	{
		try 
		{
			fileReader = new FileReader(filename);
			file = new BufferedReader(fileReader);
		}
		catch(Exception e) 
		{
			System.out.println("File not found or could not be opened.");
		}
	}
	
	public ArrayList<String> read() 
	{
		try 
		{
			String line;
			ArrayList<String> list = new ArrayList<String>();
			while((line = file.readLine()) != null) 
			{
				list.add(line);
			}
			file.close();
			fileReader.close();
			return list;
		}catch(Exception e) 
		{
			System.out.println("Cannot read buffer.");
		}
		return null;
	}
}
