package by.wgdetective.information.resolver.core.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Wladimir Litvinov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KPLinkRequest {
    private String link;
    private LocalDate date;
    private Long rate;
}
