package hotel.model;

// ============================================================
// DepartmentType
// The departments of the hotel (the same ones used in
// Applicants_Form.txt). A person can only be placed in one of
// these departments.
// ============================================================
public enum DepartmentType {
    IT_DEVELOPMENT("IT Development"),
    SALES("Sales"),
    HR("HR"),
    FINANCE("Finance"),
    MARKETING("Marketing"),
    OPERATIONS("Operations"),
    ACCOUNTING("Accounting"),
    TECHNICAL_SUPPORT("Technical Support"),
    CUSTOMER_SERVICE("Customer Service");

    private final String label; // text shown to the user

    DepartmentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    // Finds the department from the "Department" column of the file.
    // Returns null when the department does not exist.
    public static DepartmentType fromLabel(String text) {
        for (DepartmentType type : values()) {
            if (type.label.equalsIgnoreCase(text.trim())) {
                return type;
            }
        }
        return null;
    }
}
