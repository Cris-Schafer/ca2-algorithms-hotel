package hotel.model;

// ============================================================
// Employee (parent class 3 of 3)
// Subclasses: PermanentEmployee, ContractEmployee, Intern.
// The subclass follows the "Position" column of the file.
// ============================================================
public abstract class Employee {

    private final String name;
    private final String jobTitle; // the "Designation" shown in the tree
    private final Manager manager;
    private final Department department;

    protected Employee(String name, String jobTitle, Manager manager, Department department) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.manager = manager;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    // Designation = the job title of the person (for managers it is the manager type).
    public String getDesignation() {
        return jobTitle;
    }

    public Manager getManager() {
        return manager;
    }

    public Department getDepartment() {
        return department;
    }

    // The type of contract, defined by each subclass.
    public abstract String getEmploymentType();

    // Compares only the names, ignoring upper and lower case.
    // Sorting and searching both use this, so they always agree.
    public int compareNameTo(String otherName) {
        return name.compareToIgnoreCase(otherName);
    }

    // Creates the right subclass from the "Position" text ("intern", "contract" or anything else).
    public static Employee create(String name, String jobTitle, String position, Manager manager, Department department) {
        String employmentText = position.trim().toLowerCase();

        if (employmentText.equals("intern")) {
            return new Intern(name, jobTitle, manager, department);
        }
        if (employmentText.equals("contract")) {
            return new ContractEmployee(name, jobTitle, manager, department);
        }
        return new PermanentEmployee(name, jobTitle, manager, department);
    }

    @Override
    public String toString() {
        return name + " | " + getDesignation() + " | " + department.getName();
    }
}
