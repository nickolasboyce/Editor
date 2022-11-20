import java.util.*;
import java.lang.*;
import java.io.*;

public class WriteoutFile {
	private String filename;
	private String thestring;
	private ArrayList<String> thelines;

	public WriteoutFile() {}

	public void writeFromString(String filename,String thestring) {

		try {
			FileWriter fileOut = new FileWriter(filename);
				fileOut.write(thestring);
				fileOut.close();

		} catch (IOException e) {
				System.out.println("Error -- " + e.toString());
		}

   }

   public void writeFromArrayList(String filename,ArrayList<String> thelines) {

		try {
 			FileWriter fileOut = new FileWriter(filename);
				for (int i= 0; i< thelines.size(); i++) {
				String line = thelines.get(i);
				fileOut.write(line);
		}
				fileOut.close();

		} catch (IOException e) {
			System.out.println("Error : " + e.toString());
		}

   }


}
