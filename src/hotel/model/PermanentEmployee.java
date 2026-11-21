package hotel.model;

// Employee type: a permanent, open-ended position.
public class PermanentEmployee extends Employee {

    // Passes every field to the Employee parent class.
    public PermanentEmployee(String name, String jobTitle, Manager manager, Department department) {
        super(name, jobTitle, manager, department);
    }

    // Text shown as the employment type when an employee is found with SEARCH.
    @Override
    public String getEmploymentType() {
        return "Permanent";
    }
}
