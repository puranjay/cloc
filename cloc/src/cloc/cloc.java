/**
 *  Program calculates lines of code in a given source file.
 */
package cloc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Puranjay Sharma
 * 
 */
public class cloc{

	public static void main(String[] args) throws IOException {

		String filename;  	//Stores the name of the input file
		int emptylines = 0;	//Stores the number of empty lines
		int codelines = 0;	//Stores the number of code lines
		int commentlines = 0;	//Stores the number of comment lines
		int comflag = 0;	//Flag is -1 if a comment is currently open, 0 if not
		
		/* Get file name input */
		try (Scanner inp = new Scanner(System.in)) {
			System.out.print("Please enter the name of the file:	");
			filename = inp.nextLine();
		} catch (NullPointerException e) {
			System.out.println("No file name was entered.");
			return;
		}
		
		/* Use Buffered Reader to read the input file line by line*/
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			/*Read the file line-by-line and store current line as a String variable 'line'*/
			for (String line; (line = br.readLine()) != null;) {
				
				/*Check if the current line is empty*/
				if (line.isEmpty())
					emptylines++;
				
				else {
					/*Check if the line starts a comment*/
					if (line.trim().startsWith("/*")) {
						comflag = -1;  //Indicates that a comment has begun and is still open
						commentlines++;
					}
					
					/*Check if a comment is still open*/						
					else if (comflag == -1) {
						/*Check if the comment is continued*/
						if (line.trim().startsWith("*")) {
								commentlines++;
						}
						else
							comflag = 0; //If comment is closed, set flag back to zero
					}
					
					/*If the line is neither empty, not a comment, add it to LOC*/
					else if (comflag == 0)
						codelines++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("\nThe file could not be found. Please check the name and try again.");
			return;
		}
		
		System.out.println("Empty lines:	" + emptylines);
		System.out.println("Comment lines:	" + commentlines);
		System.out.println("Code lines:	" + codelines);
	}
}
