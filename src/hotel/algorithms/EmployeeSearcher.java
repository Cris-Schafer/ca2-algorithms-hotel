package hotel.algorithms;

import hotel.model.Employee;

// ============================================================
// EmployeeSearcher
// Recursive Binary Search by name. The array MUST be sorted A-Z
// with EmployeeSorter first.
// Time: O(log n). Extra memory: O(log n) for the recursion.
// ============================================================
public final class EmployeeSearcher {

    private EmployeeSearcher() {
        // Utility class: no objects are needed.
    }

    // Public entry point: returns the employee found, or null if there is none.
    public static Employee find(Employee[] sortedEmployees, String name) {
        return binarySearch(sortedEmployees, name, 0, sortedEmployees.length - 1);
    }

    // Recursive step: looks for the name between low and high.
    private static Employee binarySearch(Employee[] sortedEmployees, String name, int low, int high) {
        // Base case: nothing left to look at, so the name is not in the list.
        if (low > high) {
            return null;
        }

        int middle = (low + high) / 2;
        int comparison = sortedEmployees[middle].compareNameTo(name);

        if (comparison == 0) {
            return sortedEmployees[middle]; // found it
        }
        if (comparison > 0) {
            // The middle name is after the wanted name: search the left half.
            return binarySearch(sortedEmployees, name, low, middle - 1);
        }
        // The middle name is before the wanted name: search the right half.
        return binarySearch(sortedEmployees, name, middle + 1, high);
    }
}
