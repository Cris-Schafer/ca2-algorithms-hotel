package hotel.model;

public class Intern extends Employee {

    public Intern(String name, String jobTitle, Manager manager, Department department) {
        super(name, jobTitle, manager, department);
    }

    @Override
    public String getEmploymentType() {
        return "Intern";
    }
}
