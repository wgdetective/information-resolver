package by.wgdetective.information.resolver.core.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wladimir Litvinov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcmpTasksRequest {
    private String timestamp;
    private String fio;
    private Long acmpId;
}
