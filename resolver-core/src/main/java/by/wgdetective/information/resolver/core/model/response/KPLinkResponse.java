package by.wgdetective.information.resolver.core.model.response;

import by.wgdetective.information.resolver.core.config.KPLinkCsvConfig;
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
public class KPLinkResponse implements CsvSerializable {
    private String name;
    private String director;
    private String year;
    private String countries;
    private String mainActors;
    private String kpRate;
    private LocalDate date;
    private Long rate;

    @Override
    public String toCsv() {
        final StringJoiner joiner = new StringJoiner(KPLinkCsvConfig.OUTPUT_FIELD_DELIMITER);
        joiner.add(name).add(director).add(year).add(countries).add(mainActors)
              .add(kpRate.replace(".", ","))
              .add(date.format(KPLinkCsvConfig.DATE_TIME_FORMATTER));
        if (rate == null) {
            joiner.add("");
        } else {
            joiner.add(String.valueOf(rate));
        }
        return joiner.toString();
    }
}
