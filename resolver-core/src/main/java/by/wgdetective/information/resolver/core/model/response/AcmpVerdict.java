package by.wgdetective.information.resolver.core.model.response;

/**
 * @author Wladimir Litvinov
 */
public enum AcmpVerdict {
    PROFILE_NOT_FOUND("Профиль не найден"), TASKS_SOLVED("Задачи решены"), TASKS_NOT_SOLVED("Задачи не решены");

    private final String description;

    AcmpVerdict(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
