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
		
		System.out.println("Scanner is initialized.\n-----------------------\nCurrent file directory: " +
				DIRECTORY + "\nInput 'quit' to quit\nInput 'help' for command list\n");
		do {
			System.out.println("Awaiting next command...\n");
			userIn = scan.nextLine();
			if(userIn.contentEquals("help")) {
				File input = new File(DIRECTORY + "commands" + FILE_EXTENSION);
				Scanner tempScan = null;
				try {
					tempScan = new Scanner(input);
				} catch (FileNotFoundException e) {
					System.out.println("No command documentation found. Terminating 'help' command...\n");
					e.printStackTrace();
					break;
				}
				while(tempScan.hasNext()) {
					System.out.println(tempScan.nextLine());
				}
				tempScan.close();
			}
			else if(userIn.contains("scan")) {
				String in = "";
				String out = "";
				boolean io = false;
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
						scanInputFile(in, out);
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
		} while(! (userIn.contentEquals("quit")));
		
		System.out.println("\nShutting down...");
		scan.close();
	}
	
	private static void scanInputFile(String in, String out) {
		
	}

}
