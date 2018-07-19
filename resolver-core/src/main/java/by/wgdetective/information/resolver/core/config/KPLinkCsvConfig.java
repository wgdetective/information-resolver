package by.wgdetective.information.resolver.core.config;

import java.time.format.DateTimeFormatter;

/**
 * @author Wladimir Litvinov
 */
public class KPLinkCsvConfig {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public static final String INPUT_FIELD_DELIMITER = ";";
    public static final String OUTPUT_FIELD_DELIMITER = "\t";
    public static final String LINE_DELIMITER = "\n";

    public static final String LABEL_YEAR = "год";
    public static final String LABEL_COUNTRY = "страна";
    public static final String LABEL_DIRECTOR = "режиссер";

    public static final String FILM_NAME_PATH = "//*[@id=\"headerFilm\"]/h1";

    public static final String COMMON_INFO_PATH = "td[2]/div/a";
    public static final String DIRECTOR_INFO_PATH = "td[2]/a";

    public static final String NO_IMAGES_YEAR = "td[2]/div";

    public static final int MAX_ACTORS = 3;
    public static final long SCRIPTS_TIMEOUT = 15;
}
