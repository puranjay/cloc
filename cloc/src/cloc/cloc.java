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
 * @author puranjay
 * 
 */
public class cloc{

	public static void main(String[] args) throws IOException {

		String filename;
		int emptylines = 0;
		int codelines = 0;
		int commentlines = 0;
		int comflag = 0;

		try (Scanner inp = new Scanner(System.in)) {
			System.out.print("Please enter the name of the file:	");
			filename = inp.nextLine();
		}

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for (String line; (line = br.readLine()) != null;) {

				if (line.isEmpty())
					emptylines++;
				
				else {

					if (line.trim().startsWith("/*")) {
						comflag = -1;
						commentlines++;
					}
						
					else if (comflag == -1) {
						if (line.trim().startsWith("*")) {
								commentlines++;
						}
						else
							comflag = 0;
					}
					
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