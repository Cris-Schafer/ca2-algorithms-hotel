package hotel.menu;

import hotel.algorithms.EmployeeSearcher;
import hotel.algorithms.EmployeeSorter;
import hotel.model.Department;
import hotel.model.DepartmentType;
import hotel.model.Employee;
import hotel.model.Manager;
import hotel.model.ManagerType;
import hotel.tree.EmployeeTree;
import hotel.util.ApplicantFileReader;
import hotel.util.ConsoleInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// ============================================================
// HotelMenu
// Runs the console menu and connects the user choices to the
// sort, search, add and tree features.
// ============================================================
public class HotelMenu {

    private static final int NAMES_TO_DISPLAY = 20; // the sort shows only the first 20 names
    private static final int TREE_SIZE = 20;        // the tree is built with 20 employees

    private List<Employee> employees = new ArrayList<>();         // all records, in file order
    private final List<Employee> newlyAdded = new ArrayList<>();  // records added by the user
    private Employee[] sortedEmployees;                           // null = needs sorting again

    public void run() {
        System.out.println("===== Hotel Organisation =====");
        loadFile();

        MenuOption option;
        do {
            printMenu();
            int number = ConsoleInput.readNumber("Choose an option: ", 1, MenuOption.values().length);
            option = MenuOption.fromNumber(number);
            System.out.println();

            switch (option) {
                case SORT:
                    handleSort();
                    break;
                case SEARCH:
                    handleSearch();
                    break;
                case ADD_RECORDS:
                    handleAddRecords();
                    break;
                case CREATE_BINARY_TREE:
                    handleBinaryTree();
                    break;
                case EXIT:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (option != MenuOption.EXIT);
    }

    // ---------- File ----------

    // Asks for the file name until a file is read successfully.
    private void loadFile() {
        while (true) {
            String fileName = ConsoleInput.readText("Please enter the filename to read: ");
            try {
                employees = ApplicantFileReader.read(fileName);
                if (employees.isEmpty()) {
                    System.out.println("The file has no names. Please try another file.");
                    continue;
                }
                System.out.println("File read successfully (" + employees.size() + " names)");
                return;
            } catch (IOException e) {
                System.out.println("Could not read the file: " + e.getMessage());
            }
        }
    }

    // ---------- Menu ----------

    private void printMenu() {
        System.out.println();
        System.out.println("Do you wish to SORT or SEARCH:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println((option.ordinal() + 1) + ". " + option.getLabel());
        }
    }

    // ---------- SORT ----------

    private void handleSort() {
        System.out.println("SORT selected");
        sortEmployees();

        int amount = Math.min(NAMES_TO_DISPLAY, sortedEmployees.length);
        System.out.println("First " + amount + " names (A-Z) of " + sortedEmployees.length + ":");
        for (int i = 0; i < amount; i++) {
            System.out.println((i + 1) + ". " + sortedEmployees[i].getName());
        }
    }

    // Sorts a copy of the list, so the original file order stays the same (the tree uses it).
    // If the list is already sorted, nothing is done.
    private void sortEmployees() {
        if (sortedEmployees != null) {
            return;
        }
        sortedEmployees = employees.toArray(new Employee[0]);
        EmployeeSorter.mergeSort(sortedEmployees);
    }

    // ---------- SEARCH ----------

    private void handleSearch() {
        System.out.println("SEARCH selected");
        sortEmployees(); // binary search needs a sorted list

        String name = ConsoleInput.readText("Enter the name to search: ");
        Employee found = EmployeeSearcher.find(sortedEmployees, name);

        if (found == null) {
            System.out.println("\"" + name + "\" was not found.");
            return;
        }
        System.out.println("Name:            " + found.getName());
        System.out.println("Manager Type:    " + found.getManager().getTitle());
        System.out.println("Department:      " + found.getDepartment().getName());
        System.out.println("Job Title:       " + found.getJobTitle());
        System.out.println("Employment Type: " + found.getEmploymentType());
    }

    // ---------- ADD RECORDS ----------

    private void handleAddRecords() {
        System.out.println("ADD RECORDS selected");

        String name = ConsoleInput.readName("Please input the name: ");

        // A name that already exists may be a typing mistake, so the user must confirm it.
        if (nameExists(name) && !ConsoleInput.readYesNo("\"" + name + "\" is already in the list. Add it again?")) {
            System.out.println("Record not added.");
            return;
        }

        // Only the manager types that exist are listed, so the choice is always valid.
        System.out.println("Please select from the following Management Staff:");
        ManagerType[] managerTypes = ManagerType.values();
        for (int i = 0; i < managerTypes.length; i++) {
            System.out.println((i + 1) + ". " + managerTypes[i].getLabel());
        }
        ManagerType managerType = managerTypes[ConsoleInput.readNumber("Option: ", 1, managerTypes.length) - 1];

        // Same for the departments.
        System.out.println("Please select the Department:");
        DepartmentType[] departmentTypes = DepartmentType.values();
        for (int i = 0; i < departmentTypes.length; i++) {
            System.out.println((i + 1) + ". " + departmentTypes[i].getLabel());
        }
        DepartmentType departmentType = departmentTypes[ConsoleInput.readNumber("Option: ", 1, departmentTypes.length) - 1];

        // The job title of a new record is its manager type; a new record is a permanent employee.
        Employee employee = Employee.create(name, managerType.getLabel(), "",
                Manager.create(managerType), Department.create(departmentType));
        employees.add(employee);
        newlyAdded.add(employee);
        sortedEmployees = null; // the list changed, so it must be sorted again before the next search

        System.out.println("\"" + name + "\" has been added as \"" + managerType.getLabel()
                + "\" to \"" + departmentType.getLabel() + "\" successfully!");

        System.out.println();
        System.out.println("Newly added records (" + newlyAdded.size() + "):");
        for (Employee added : newlyAdded) {
            System.out.println("  " + added);
        }
    }

    // True if a record with this name already exists (upper and lower case are ignored).
    private boolean nameExists(String name) {
        for (Employee employee : employees) {
            if (employee.compareNameTo(name) == 0) {
                return true;
            }
        }
        return false;
    }

    // ---------- BINARY TREE ----------

    private void handleBinaryTree() {
        System.out.println("Create Binary Tree selected");

        if (employees.size() < TREE_SIZE) {
            System.out.println("At least " + TREE_SIZE + " records are needed. The list has " + employees.size() + ".");
            return;
        }

        // Order all records by rank (Head Manager first, Staff last). This sort keeps the file
        // order inside each rank. The 20 highest ranks go into the tree, so the top of the
        // hierarchy is always at the top of the tree.
        List<Employee> byRank = new ArrayList<>(employees);
        byRank.sort(Comparator.comparingInt(employee -> employee.getManager().getRank()));

        EmployeeTree tree = new EmployeeTree();
        for (Employee employee : byRank.subList(0, TREE_SIZE)) {
            tree.insert(employee);
        }

        System.out.println("Employee hierarchy (level order):");
        tree.printLevelOrder();
        System.out.println();
        System.out.println("Tree height (levels): " + tree.height());
        System.out.println("Total number of nodes: " + tree.countNodes());
    }
}
