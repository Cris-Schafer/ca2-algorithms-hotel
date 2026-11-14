package hotel.model;

// The top of the hotel hierarchy (rank 1).
public class HeadManager extends Manager {

    public HeadManager() {
        super(ManagerType.HEAD_MANAGER, 1);
    }
}
