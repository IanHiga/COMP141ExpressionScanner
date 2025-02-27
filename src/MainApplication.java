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
 * Project Phase: 1.2
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
			readFile(input, output);
		}
	}
	
	private static void readFile(File in, File out) {
		FileWriter tokenOut;

		if(in.exists()){		
			//PROCEED WITH SCAN
			Scanner scan;
			try {
				scan = new Scanner(in);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			try {
				out.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				scan.close();
				return;
			}
			
			try {
				tokenOut = new FileWriter(out);
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
			if(! in.exists()) {
				System.out.println("Input file " + DIRECTORY + in + FILE_EXTENSION + " does not exist.\n");
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
			else if(Pattern.matches("[+|\\-|*|/|(|)|;]", temp) || Pattern.matches("[:]|[:][=]", temp)) {
				if(!Pattern.matches("[:]", temp)) {		
					tokenType = "SYMBOL";
				}
			}
			else if(Pattern.matches("[[a-z]|[A-Z]][[a-z]|[A-Z]|[0-9]]*", temp)) {
				if(temp.contentEquals("if") || temp.contentEquals("then") || temp.contentEquals("elseif") || temp.contentEquals("while")
						|| temp.contentEquals("do") || temp.contentEquals("endwhile") || temp.contentEquals("skip")) {
					tokenType = "KEYWORD";
				}
				else if(!(Pattern.matches("[a-z]|[A-Z]|[0-9]", next))) {					
					tokenType = "IDENTIFIER";
				}
			}
			else if(!temp.contentEquals("") && !next.contentEquals("\t")){
				tokenType = "ERROR";
				tokens += "ERROR READING '" + temp + "'!\n\n";
				return(tokens);
			}
			
			if(!tokenType.contentEquals("")) {	
				tokens += temp + " : " + tokenType + "\n";
				temp = "";
			}
			if(!next.contentEquals(" ") && !next.contentEquals("\t")) {		
				temp += next;
			}
		}
		tokens += "\n";
		return(tokens);
	}

}
