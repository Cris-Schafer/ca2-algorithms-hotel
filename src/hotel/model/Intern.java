package hotel.model;

// Employee type: a temporary position focused on learning.
public class Intern extends Employee {

    // Passes every field to the Employee parent class.
    public Intern(String name, String jobTitle, Manager manager, Department department) {
        super(name, jobTitle, manager, department);
    }

    // Text shown as the employment type when an employee is found with SEARCH.
    @Override
    public String getEmploymentType() {
        return "Intern";
    }
}
