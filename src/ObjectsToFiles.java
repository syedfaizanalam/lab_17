import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ObjectsToFiles {

	// Note: NIO uses the Path class, not just a file path String.
	private static Path filePath = Paths.get("countries.txt");

	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		System.out.println(filePath.toAbsolutePath());
		
		while (true) {
			System.out.print("Enter a command (list, add, quit): ");
			String command = scnr.nextLine();
			if (command.equals("quit")) {
				break;
			} else if (command.equals("list")) {
				List<Countries> things = readFile();
				int i = 1; 
				for (Countries thing : things) {
					System.out.println(i++ + ". " + thing);
				}
			} else if (command.equals("add")) {
				Countries player = getPlayerFromUser(scnr);
				System.out.println("Adding " + player);
				appendLineToFile(player);
			} else {
				System.out.println("Invalid command.");
			}
		}
		System.out.println("Goodbye.");
		scnr.close();
	}
	
	private static Countries getPlayerFromUser(Scanner scnr) {
		// TODO #1 adjust this for your class, not Player
		String name = Validator.getString(scnr, "Enter name: ");
		int jersey = Validator.getInt(scnr, "Enter jersey number: ");
		boolean retired = Validator.getYesNo(scnr, "Is the player retired (yes/no)? ");
		return new Countries(name, jersey, retired);
	}

	/**
	 * Read all the objects from a file and store them in a List.
	 */
	public static List<Countries> readFile() {
		try {
			List<String> lines = Files.readAllLines(filePath);
			// TODO #2 Add code here... convert this list of String lines from
			// the file to a list of our objects. (Player, in my case)
			List<Countries> players = new ArrayList<>();
			for (String line : lines) {
				// #1 split the line based on the delimiter
				String[] parts = line.split("~~~");
				// #2 select each part based on index position and convert
				// to the correct type
				String name = parts[0];
				int jersey = Integer.parseInt(parts[1]);
				boolean retired = Boolean.parseBoolean(parts[2]);
				// #3 use the data to create an object and add it to the list
				players.add(new Countries(name, jersey, retired));
			}
			
			return players;
		} catch (IOException e) {
			System.out.println("Unable to read file.");
			return new ArrayList<>();
		}
	}

	/**
	 * Add an object to the end of the file.
	 */
	public static void appendLineToFile(Countries thing) {
		// TODO #3 add code here... convert your object to a string like
		// it should be as a line in the file. store it in the `line` variable.
		String line = thing.getNameOfCountry() + "~~~" + thing.getPopulation() + "~~~" + thing.isInWesternHemisphere();
		//line = String.format("%s~~~%d~~~%s", thing.getName(), thing.getJersey(), thing.isRetired());
		
		// Files.write requires a list of lines. Create a list of one line.
		List<String> lines = Collections.singletonList(line);
		try {
			Files.write(filePath, lines, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}

}