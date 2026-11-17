package hotel.algorithms;

import hotel.model.Employee;

// ============================================================
// EmployeeSorter
// Recursive Merge Sort, sorting employees A-Z by name.
// Time: O(n log n) in every case. Extra memory: O(n).
// ============================================================
public final class EmployeeSorter {

    private EmployeeSorter() {
        // Utility class: no objects are needed.
    }

    // Public entry point: sorts the whole array.
    public static void mergeSort(Employee[] employees) {
        if (employees.length < 2) {
            return; // nothing to sort
        }
        Employee[] temp = new Employee[employees.length]; // one helper array, reused by every merge
        mergeSort(employees, temp, 0, employees.length - 1);
    }

    // Recursive step: sorts the part of the array between left and right.
    private static void mergeSort(Employee[] employees, Employee[] temp, int left, int right) {
        // Base case: one element (or none) is already sorted.
        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;

        mergeSort(employees, temp, left, middle);      // sort the left half
        mergeSort(employees, temp, middle + 1, right); // sort the right half
        merge(employees, temp, left, middle, right);   // join the two sorted halves
    }

    // Joins two sorted parts: [left..middle] and [middle+1..right].
    private static void merge(Employee[] employees, Employee[] temp, int left, int middle, int right) {
        int i = left;       // position in the left part
        int j = middle + 1; // position in the right part
        int k = left;       // position in the temp array

        while (i <= middle && j <= right) {
            // "<= 0" takes the left one first when names are equal, so the sort is stable.
            if (employees[i].compareNameTo(employees[j].getName()) <= 0) {
                temp[k++] = employees[i++];
            } else {
                temp[k++] = employees[j++];
            }
        }

        // Copy what is left of each part.
        while (i <= middle) {
            temp[k++] = employees[i++];
        }
        while (j <= right) {
            temp[k++] = employees[j++];
        }

        // Copy the merged result back into the original array.
        for (int x = left; x <= right; x++) {
            employees[x] = temp[x];
        }
    }
}
