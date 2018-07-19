package by.wgdetective.information.resolver.core.model.response;

import by.wgdetective.information.resolver.core.config.AcmpTasksConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Override
    public String toCsv() {
        return acmpId + AcmpTasksConfig.OUTPUT_FIELD_DELIMITER + verdict.getDescription();
    }
}
