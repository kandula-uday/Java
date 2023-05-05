import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Author : Uday Shankar
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	 Scanner scanner = new Scanner(System.in);

         // Get input from the user for directory path
         System.out.print("Enter the directory path: ");
         String directoryPath = scanner.nextLine();

         // Get input from the user for output directory path
         System.out.print("Enter the output directory path: ");
         String outputFilePath = scanner.nextLine();

         // Get input from the user for substring to search
         System.out.print("Enter the substring to search: ");
         String find = scanner.nextLine();
    	//String directoryPath = "/Users/udayshankar/Downloads/Statements"; // Change this to your directory path
    	//String outputFilePath = "/Users/udayshankar/Downloads/Statements_output/output.txt";
    	File outputFile = new File(outputFilePath);
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf")); // Only match files with .pdf extension
        for(File file : files) {
        	try(PDDocument document = PDDocument.load(file)) {
        		System.out.println(file);
        		//Instantiate PDFTextStripper class
    	        PDFTextStripper pdfStripper = new PDFTextStripper();
    	        //Retrieving text from PDF document
    	        String text = pdfStripper.getText(document);
    	        //String find = "KAND";
    	        String[] lines = text.split("\n");
    	        for(String line: lines) {
    	        	if(line.contains(find)) {
    	        		//FileUtils.writeStringToFile(outputFile, text, "UTF-8");
    	                FileUtils.writeStringToFile(outputFile, line + System.lineSeparator(), StandardCharsets.UTF_8, true);

    	        	}
    	        }
    	        document.close();
        	}
    	        catch(IOException e) {
            		e.printStackTrace();
            	}
        	}
        	
    }
}
