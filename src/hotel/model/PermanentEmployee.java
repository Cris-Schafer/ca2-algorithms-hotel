package hotel.model;

public class PermanentEmployee extends Employee {

    public PermanentEmployee(String name, String jobTitle, Manager manager, Department department) {
        super(name, jobTitle, manager, department);
    }

    @Override
    public String getEmploymentType() {
        return "Permanent";
    }
}
