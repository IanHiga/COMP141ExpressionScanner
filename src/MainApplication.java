import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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
					return;
				}
				
				do {
					scanInputLine(scan.nextLine(), output);
				} while(scan.hasNext());
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
	
	private static void scanInputLine(String in, File out) {
		char[] tokenChars = in.toCharArray();
		String temp = "";
		for(int i = 0; i < in.length(); i++) {
			if(tokenChars[i] == ' ') {
				if(Pattern.matches("\\d+", temp)) {
					System.out.println(temp + " is a digit");
				}
				else if(Pattern.matches("[+ | \\- | * | / | ( | )]", temp)) {
					System.out.println(temp + " is an operator");
				}
				else if(Pattern.matches("[[a-z] | [A-Z]][[a-z] | [A-Z] | [0-9]]+", temp)){
					System.out.println(temp + " is an identifier");
				}
				else {
					System.out.println(temp + " is not defined");
				}
				temp = "";
			}
			else {				
				temp += tokenChars[i];
			}
		}
	}

}
