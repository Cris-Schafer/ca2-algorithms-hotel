package hotel.model;

// Lowest level (rank 6): people who do not manage anyone.
public class StaffMember extends Manager {

    public StaffMember() {
        super(ManagerType.STAFF, 6);
    }
}
