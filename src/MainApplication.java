import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainApplication {
	public static final String DIRECTORY = "files/";
	public static final String FILE_EXTENSION = ".txt";
	private File input, output;
	
	public static void main(String[] args) {
		String userIn = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Scanner is initialized.\n-----------------------\nCurrent file directory: " +
				DIRECTORY + "\nInput 'quit' to quit\nInput 'help' for command list\n");
		//String test = userIn.nextLine();
		do {
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
		} while(! (userIn.contains("quit")));
		
		System.out.println("\nShutting down...");
		scan.close();
	}

}
