import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
		else if(in.contentEquals("commands") || out.contentEquals("commands")){
			System.out.println("An error has occured:\nCannot use commands file as input or output.\n");
		}
		else {
			File input = new File(DIRECTORY + in + FILE_EXTENSION);
			File output = new File(DIRECTORY + out + FILE_EXTENSION);

			if(input.exists() && ! (output.exists())){		
				Scanner inScan = new Scanner(DIRECTORY + in + FILE_EXTENSION);
				
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
		scan.close();
	}
	
	private static void scanInputLine(String in) {
		
	}

}
