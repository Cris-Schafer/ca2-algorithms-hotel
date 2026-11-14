package hotel.model;

// Departments that keep the hotel running day to day.
public class OperationsDepartment extends Department {

    public OperationsDepartment(DepartmentType type) {
        super(type);
    }

    @Override
    public String getArea() {
        return "Operations";
    }
}
