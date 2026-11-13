package hotel.model;

// ============================================================
// ManagerType
// The management levels of the hotel, from the highest to the
// lowest. Used by the menu, by the file reader and by the
// validation, so a type that is not here cannot be chosen.
// STAFF is the lowest level: people who do not manage anyone.
// ============================================================
public enum ManagerType {
    HEAD_MANAGER("Head Manager"),
    SENIOR_MANAGER("Senior Manager"),
    MANAGER("Manager"),
    ASSISTANT_MANAGER("Assistant Manager"),
    TEAM_LEAD("Team Lead"),
    STAFF("Staff");

    private final String label; // text shown to the user

    ManagerType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    // Finds the management level from the "Job title" column of the file.
    // Any job title that is not a management title (Clerk, QA...) is STAFF.
    public static ManagerType fromJobTitle(String jobTitle) {
        for (ManagerType type : values()) {
            if (type != STAFF && type.label.equalsIgnoreCase(jobTitle.trim())) {
                return type;
            }
        }
        return STAFF;
    }
}
