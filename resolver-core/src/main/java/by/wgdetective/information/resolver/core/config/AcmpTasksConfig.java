package by.wgdetective.information.resolver.core.config;

/**
 * @author Wladimir Litvinov
 */
public class AcmpTasksConfig {
    public static final String INPUT_FIELD_DELIMITER = ",";
    public static final String OUTPUT_FIELD_DELIMITER = ";";
    public static final String LINE_DELIMITER = "\n";

    public static final String LINK_PREFIX = "http://acmp.ru/index.asp?main=user&id=";
    public static final String SOLVED_TASKS_LINK = "html/body/table/tbody/tr[3]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr/td[1]/p[1]";

    public static final String REQUIRED_TASKS = "requiredTasks";
}
