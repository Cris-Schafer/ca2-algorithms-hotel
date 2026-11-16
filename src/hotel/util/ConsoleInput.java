package hotel.util;

import java.util.Scanner;

// ============================================================
// ConsoleInput
// Reads and validates what the user types. Every method repeats
// the question until the answer is valid, so the program never
// crashes because of a wrong input.
// ============================================================
public final class ConsoleInput {

    private static final Scanner SCANNER = new Scanner(System.in);

    // Letters (including accents), spaces, dots, apostrophes and hyphens; 2 to 50 characters.
    private static final String NAME_PATTERN = "\\p{L}[\\p{L} .'-]{1,49}";

    private ConsoleInput() {
        // Utility class: no objects are needed.
    }

    // Reads any non-empty text.
    public static String readText(String question) {
        while (true) {
            System.out.print(question);
            String text = SCANNER.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("Invalid input. Please type something.");
        }
    }

    // Reads a whole number between min and max (both included).
    public static int readNumber(String question, int min, int max) {
        while (true) {
            System.out.print(question);
            String text = SCANNER.nextLine().trim();
            try {
                int number = Integer.parseInt(text);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // falls through to the message below
            }
            System.out.println("Invalid option. Please type a number between " + min + " and " + max + ".");
        }
    }

    // Reads a person name made of valid characters only.
    public static String readName(String question) {
        while (true) {
            System.out.print(question);
            String name = SCANNER.nextLine().trim().replaceAll("\\s+", " ");
            if (name.matches(NAME_PATTERN)) {
                return name;
            }
            System.out.println("Invalid name. Use 2 to 50 letters (spaces, . ' - are allowed).");
        }
    }
}
