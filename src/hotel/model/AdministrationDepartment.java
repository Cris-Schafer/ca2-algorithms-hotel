package hotel.model;

// Back-office departments: people and money.
public class AdministrationDepartment extends Department {

    public AdministrationDepartment(DepartmentType type) {
        super(type);
    }

    @Override
    public String getArea() {
        return "Administration";
    }
}
