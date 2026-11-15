package hotel.model;

public class ContractEmployee extends Employee {

    public ContractEmployee(String name, String jobTitle, Manager manager, Department department) {
        super(name, jobTitle, manager, department);
    }

    @Override
    public String getEmploymentType() {
        return "Contract";
    }
}
