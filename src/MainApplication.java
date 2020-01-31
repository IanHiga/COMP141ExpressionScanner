import java.io.File;
import java.util.Scanner;

public class MainApplication {
	public static final String DIRECTORY = "null"; //TODO add file directory
	private File input, output;
	
	public static void main(String[] args) {
		String userIn;
		Scanner scan = new Scanner(System.in);
		System.out.println("Scanner is initialized.\n-----------------------\nCurrent file directory: " +
				DIRECTORY + "\nInput 'help' for command list\n");
		//String test = userIn.nextLine();
		
		
		scan.close();
	}

}
