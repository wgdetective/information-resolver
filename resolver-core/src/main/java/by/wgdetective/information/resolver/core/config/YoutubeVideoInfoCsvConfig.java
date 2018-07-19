package by.wgdetective.information.resolver.core.config;

import java.time.format.DateTimeFormatter;

/**
 * @author Wladimir Litvinov
 */
public class YoutubeVideoInfoCsvConfig {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public static final String INPUT_FIELD_DELIMITER = ";";
    public static final String OUTPUT_FIELD_DELIMITER = "\t";
    public static final String LINE_DELIMITER = "\n";

    public static final String LINK_PREFIX = "https://www.youtube.com/watch?v=";
    public static final String API_KEY = "AIzaSyCMc7-bH69ShDnLWQSnqWs16jVHvVGyReE";
}
