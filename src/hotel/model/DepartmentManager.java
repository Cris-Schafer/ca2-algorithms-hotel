package hotel.model;

// Manages one department (rank 3). Its title in the file is just "Manager".
public class DepartmentManager extends Manager {

    public DepartmentManager() {
        super(ManagerType.MANAGER, 3);
    }
}
