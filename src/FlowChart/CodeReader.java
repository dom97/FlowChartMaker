package FlowChart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class CodeReader {

	private String FileName;
	
	
	public CodeReader(String FileName)
	{
		this.FileName = FileName;
	}
	
	public String[] readLines()
	{
		String[] codeBody;
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
            String line = "";
            
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(FileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + FileName + "'");                
        }
        catch(IOException e) {
            e.printStackTrace();
        }
		
		int startOfBody = 0; 
		
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).contains("%%Body")) {
				startOfBody = i;
				break;
			}
		}
		
		codeBody = new String[lines.size() - startOfBody];
		
		for (int i = startOfBody; i < lines.size(); i++) {
			codeBody[i] = lines.get(i);
		}		
		
		return codeBody;
	}
}
