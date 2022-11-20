import java.util.*;
import java.lang.*;
import java.io.*;

public class ReadinFile {
	private String filename;
	private String thestring;
	private ArrayList<String> thelines;

	public ReadinFile() {}

	public String readToString(String filename) {
		StringBuilder sb= new StringBuilder();

		try (
			FileReader fileIn = new FileReader(filename);
			BufferedReader buffIn = new BufferedReader(fileIn)) {

			boolean eof = false;
			while (!eof) {
				String line = buffIn.readLine();
				if (line == null) {
					eof = true;
				} else {
					sb.append(line + "\n");
				}
			}
			//buffIn.close();

		} catch (IOException e) {
			System.out.println("Error : " + e.toString());
		}
		thestring= sb.toString();
		return thestring;
	}

	public ArrayList<String> readToArrayList(String filename) {

		try (
			FileReader fileIn = new FileReader(filename);
			BufferedReader buffIn = new BufferedReader(fileIn)) {

			boolean eof = false;
			while (!eof) {
				String line = buffIn.readLine();
				if (line == null) {
					eof = true;
				} else {
					thelines.add(line + "\n");
				}
			}
			//buffIn.close();

		} catch (IOException e) {
			System.out.println("Error : " + e.toString());
		}
		return thelines;
	}


}
