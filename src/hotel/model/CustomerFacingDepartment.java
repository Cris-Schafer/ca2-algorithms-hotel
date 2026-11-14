package hotel.model;

// Departments that deal with guests and bookings.
public class CustomerFacingDepartment extends Department {

    public CustomerFacingDepartment(DepartmentType type) {
        super(type);
    }

    @Override
    public String getArea() {
        return "Customer Facing";
    }
}
