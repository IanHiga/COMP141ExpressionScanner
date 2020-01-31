import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainApplication {
	public static final String DIRECTORY = "files/";
	public static final String FILE_EXTENSION = ".txt";
	
	public static void main(String[] args) {
		String userIn = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Scanner is initialized.\n-----------------------\nCurrent file directory: " +
				DIRECTORY + "\nInput 'quit' to quit\nInput 'help' for command list\n");
		//String test = userIn.nextLine();
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
			else if(userIn.contentEquals("scan")) {
				String temp;
				boolean accepted = false;
				
				//Input file specification
				do {
					System.out.println("Input .txt filename: ");
					temp = scan.nextLine();
					if(temp.contentEquals("quit")) {
						break;
					}
					File input = new File(DIRECTORY + temp + FILE_EXTENSION);
					if(!(input.exists())) {
						System.out.println("Input file '" + DIRECTORY + temp + FILE_EXTENSION + "' does not exist.\n"
								+ "Please enter an existing filename, or use 'quit' to exit\n");
					}
					else {
						accepted = true;
					}
				} while(!accepted);
				//Output file specification
				if(!(temp.contentEquals("quit"))) {					
					System.out.println("Output .txt filename: ");
					File output = new File(DIRECTORY + temp + FILE_EXTENSION);
				}
			}
		} while(! (userIn.contentEquals("quit")));
		
		System.out.println("\nShutting down...");
		scan.close();
	}

}
