import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadFile<T>{
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<T> read() {
		try {
			T line;
			ArrayList<T> list = new ArrayList<T>();
			while((line = (T) file.readLine()) != null) {
				list.add(line);
			}
			file.close();
			fileReader.close();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
