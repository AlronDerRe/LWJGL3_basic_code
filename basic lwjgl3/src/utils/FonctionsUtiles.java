package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.ByteBuffer;

public abstract class FonctionsUtiles {

	public static String loadFileInString(String filePath)
	{
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader buf = new BufferedReader(new FileReader(filePath));
			String line = new String();
			
			while((line = buf.readLine()) != null){
				builder.append(line);
				builder.append("\n");
			}
			buf.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
}
