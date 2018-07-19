package by.wgdetective.information.resolver.core.model.response;

import by.wgdetective.information.resolver.core.config.YoutubeVideoInfoCsvConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * @author Wladimir Litvinov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YoutubeVideoResponse implements CsvSerializable {
    private String title;
    private String author;
    private String link;
    private String duration;
    private LocalDate date;
    private Long rate;

    @Override
    public String toCsv() {
        final StringJoiner joiner = new StringJoiner(YoutubeVideoInfoCsvConfig.OUTPUT_FIELD_DELIMITER);
        joiner.add(title).add(author).add(link).add(duration)
              .add(date.format(YoutubeVideoInfoCsvConfig.DATE_TIME_FORMATTER));
        if (rate == null) {
            joiner.add("");
        } else {
            joiner.add(String.valueOf(rate));
        }
        return joiner.toString();
    }
}
