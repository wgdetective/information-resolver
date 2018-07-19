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
public class YoutubeVideoRequest {
    private String videoId;
    private LocalDate date;
    private Long rate;
}
