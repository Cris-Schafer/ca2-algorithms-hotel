package hotel.model;

// Employee type: hired for a fixed period under a contract.
public class ContractEmployee extends Employee {

    // Passes every field to the Employee parent class.
    public ContractEmployee(String name, String jobTitle, Manager manager, Department department) {
        super(name, jobTitle, manager, department);
    }

    // Text shown for this type of employee in the lists and in the tree.
    @Override
    public String getEmploymentType() {
        return "Contract";
    }
}
