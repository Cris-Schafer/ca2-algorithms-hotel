package hotel.menu;

// ============================================================
// MenuOption
// The options of the main menu. The menu is printed by looping
// over values(), so a new option only needs to be added here.
// ============================================================
public enum MenuOption {
    SORT("SORT"),
    SEARCH("SEARCH"),
    ADD_RECORDS("ADD RECORDS"),
    CREATE_BINARY_TREE("Create Binary Tree"),
    EXIT("EXIT");

    private final String label; // text shown in the menu

    MenuOption(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    // Menu numbers start at 1; returns the option for a number already validated by the menu.
    public static MenuOption fromNumber(int number) {
        return values()[number - 1];
    }
}
