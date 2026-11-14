package hotel.model;

// ============================================================
// Manager (parent class 1 of 3)
// Subclasses: HeadManager, SeniorManager, DepartmentManager,
// AssistantManager, TeamLead, StaffMember.
// The rank decides the position in the hierarchy (1 = top).
// ============================================================
public abstract class Manager {

    private final ManagerType type;
    private final int rank;

    protected Manager(ManagerType type, int rank) {
        this.type = type;
        this.rank = rank;
    }

    public ManagerType getType() {
        return type;
    }

    public int getRank() {
        return rank;
    }

    public String getTitle() {
        return type.getLabel();
    }

    // Creates the right subclass for a manager type.
    public static Manager create(ManagerType type) {
        switch (type) {
            case HEAD_MANAGER:
                return new HeadManager();
            case SENIOR_MANAGER:
                return new SeniorManager();
            case MANAGER:
                return new DepartmentManager();
            case ASSISTANT_MANAGER:
                return new AssistantManager();
            case TEAM_LEAD:
                return new TeamLead();
            case STAFF:
                return new StaffMember();
            default:
                throw new IllegalArgumentException("Unknown manager type: " + type);
        }
    }
}
