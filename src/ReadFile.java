import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile {
	private BufferedReader file;
	private FileReader fileReader;
	ReadFile(String filename)
	{
		try {
			fileReader = new FileReader(filename);
			file = new BufferedReader(fileReader);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] read() {
		try {
			String line = "";
			ArrayList<String> list = new ArrayList<String>();
			while((line = file.readLine()) != null) {
				list.add(line);
			}
			String[] array = new String[list.size()];
			array = list.toArray(array);
			file.close();
			fileReader.close();
			return array;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
