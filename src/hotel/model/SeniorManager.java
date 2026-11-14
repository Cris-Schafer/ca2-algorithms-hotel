package hotel.model;

// Reports to the Head Manager (rank 2).
public class SeniorManager extends Manager {

    public SeniorManager() {
        super(ManagerType.SENIOR_MANAGER, 2);
    }
}
