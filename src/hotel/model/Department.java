package hotel.model;

// ============================================================
// Department (parent class 2 of 3)
// Subclasses (the 9 departments are grouped into 3 areas):
//   CustomerFacingDepartment: Customer Service, Sales, Marketing
//   OperationsDepartment:     Operations, Technical Support, IT Development
//   AdministrationDepartment: HR, Finance, Accounting
// ============================================================
public abstract class Department {

    private final DepartmentType type;

    protected Department(DepartmentType type) {
        this.type = type;
    }

    public DepartmentType getType() {
        return type;
    }

    public String getName() {
        return type.getLabel();
    }

    // Name of the area this department belongs to, defined by each subclass.
    public abstract String getArea();

    // Creates the right subclass for a department.
    public static Department create(DepartmentType type) {
        switch (type) {
            case CUSTOMER_SERVICE:
            case SALES:
            case MARKETING:
                return new CustomerFacingDepartment(type);
            case OPERATIONS:
            case TECHNICAL_SUPPORT:
            case IT_DEVELOPMENT:
                return new OperationsDepartment(type);
            case HR:
            case FINANCE:
            case ACCOUNTING:
                return new AdministrationDepartment(type);
            default:
                throw new IllegalArgumentException("Unknown department: " + type);
        }
    }
}
