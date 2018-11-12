package by.wgdetective.information.resolver.core.model.response;

import by.wgdetective.information.resolver.core.config.AcmpTasksConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static by.wgdetective.information.resolver.core.config.AcmpTasksConfig.OUTPUT_FIELD_DELIMITER;

/**
 * @author Wladimir Litvinov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcmpTasksResponse implements CsvSerializable {
    private Long acmpId;
    private List<Long> solvedTasks;
    private AcmpVerdict verdict;
    private int solvedTasksCount;

    @Override
    public String toCsv() {
        return String
            .format("%d%s%s%s%d", acmpId, OUTPUT_FIELD_DELIMITER, verdict.getDescription(), OUTPUT_FIELD_DELIMITER,
                    solvedTasksCount);
    }
}
