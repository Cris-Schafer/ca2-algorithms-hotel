package hotel.model;

// Employee type: hired for a fixed period under a contract.
public class ContractEmployee extends Employee {

    // Passes every field to the Employee parent class.
    public ContractEmployee(String name, String jobTitle, Manager manager, Department department) {
        super(name, jobTitle, manager, department);
    }

    // Text shown as the employment type when an employee is found with SEARCH.
    @Override
    public String getEmploymentType() {
        return "Contract";
    }
}
