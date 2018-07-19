package by.wgdetective.information.resolver.core.factory;

import by.wgdetective.information.resolver.core.config.YoutubeVideoInfoCsvConfig;
import by.wgdetective.information.resolver.core.model.request.YoutubeVideoRequest;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

import static by.wgdetective.information.resolver.core.config.YoutubeVideoInfoCsvConfig.LINK_PREFIX;

/**
 * @author Wladimir Litvinov
 */
public class YoutubeVideoRequestFactory implements RequestFromArrayFactory<YoutubeVideoRequest> {

    @Override
    public YoutubeVideoRequest create(final String[] array) {
        final String videoId = parseVideoId(array[0]);
        final LocalDate viewDate =
                array.length > 1 && StringUtils.isNoneEmpty(array[1])
                ? LocalDate.parse(array[1], YoutubeVideoInfoCsvConfig.DATE_TIME_FORMATTER)
                : LocalDate.now();
        final Long rate =
                array.length > 2 && StringUtils.isNoneEmpty(array[2]) ? Long.parseLong(array[2])
                                                                      : null;
        return new YoutubeVideoRequest(videoId, viewDate, rate);
    }

    private String parseVideoId(final String link) {
        final String videoId = link.startsWith(LINK_PREFIX)
                          ? link.replace(LINK_PREFIX, "")
                          : link;
        return videoId.replaceAll("&t=\\d+s", "");
    }
}
