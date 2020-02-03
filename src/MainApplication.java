import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * COMP 141- Programming Languages
 * Name: Ian Higa
 * Project Phase: 1.1
 */

public class MainApplication {
	public static final String DIRECTORY = "files/";
	public static final String FILE_EXTENSION = ".txt";
	
	public static void main(String[] args) {
		String userIn = null;
		Scanner scan = new Scanner(System.in);
		String in = "";
		String out = "";
		boolean io = false;
		
		userIn = scan.nextLine();
		scan.close();
		char[] command = userIn.toCharArray();
				
		for(int i = 5; i < command.length; i++) {
			if(command[i] != ' ' && !io) {
				in += command[i];
			}
			else if (io) {
				if(command[i] == ' ') {
					break;
				}
				out += command[i];
			}
			else {
				io = true;
			}	
		}
				
		if(in.contentEquals("") || out.contentEquals("")) {
			System.out.println("An error has occured:\nNot enough arguments.\n");
		}
		else {
			File input = new File(DIRECTORY + in + FILE_EXTENSION);
			File output = new File(DIRECTORY + out + FILE_EXTENSION);
			FileWriter tokenOut;

			if(input.exists() && ! (output.exists())){		
				//PROCEED WITH SCAN
				try {
					scan = new Scanner(input);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				
				try {
					output.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					scan.close();
					return;
				}
				
				try {
					tokenOut = new FileWriter(output);
				} catch (IOException e) {
					e.printStackTrace();
					scan.close();
					return;
				}

				do {
					try {
						tokenOut.write(scanInputLine(scan.nextLine()));
					} catch (IOException e) {
						e.printStackTrace();
						scan.close();
						break;
					}
				} while(scan.hasNext());
				try {
					tokenOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				scan.close();
			}
			else {
				System.out.println("An error has occured:\n");
				if(! input.exists()) {
					System.out.println("Input file " + DIRECTORY + in + FILE_EXTENSION + " does not exist.\n");
				}
				if(output.exists()) {
					System.out.println("Output file " + DIRECTORY + out + FILE_EXTENSION + " already exists.\n");
				}
			}
		}
	}
	
	private static String scanInputLine(String in) {
		in += " ";
		char[] tokenChars = in.toCharArray();
		String tokenType = "";
		String temp = "";
		String next = "";
		String tokens = "Line: " + in + "\n";
		
		for(int i = 0; i < in.length(); i++) {
			next = "";
			next += tokenChars[i];
			tokenType = "";
			
			if(Pattern.matches("[0-9]+", temp)) {
				if(!Pattern.matches("[0-9]", next)) {
					tokenType = "DIGIT";
				}
			}
			else if(Pattern.matches("[+|\\-|*|/|(|)]", temp)) {
				tokenType = "OPERATOR";
			}
			else if(Pattern.matches("[[a-z]|[A-Z]][[a-z]|[A-Z]|[0-9]]*", temp)) {
				if(!(Pattern.matches("[a-z]|[A-Z]|[0-9]", next))) {
					tokenType = "IDENTIFIER";
				}
			}
			else if(!temp.contentEquals("")){
				tokenType = "ERROR";
			}
			
			if(!tokenType.contentEquals("")) {	
				tokens += temp + " : " + tokenType + "\n";
				temp = "";
			}
			if(!next.contentEquals(" ")) {		
				temp += next;
			}
		}
		tokens += "\n";
		return(tokens);
	}

}
