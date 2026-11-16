package hotel;

import hotel.menu.HotelMenu;
import java.util.NoSuchElementException;

// ============================================================
// Main
// Entry point of the Hotel Organisation program.
// ============================================================
public class Main {

    public static void main(String[] args) {
        try {
            new HotelMenu().run();
        } catch (NoSuchElementException e) {
            // Happens only when the keyboard input is closed (for example Ctrl+D).
            System.out.println("Input closed. Goodbye!");
        }
    }
}
