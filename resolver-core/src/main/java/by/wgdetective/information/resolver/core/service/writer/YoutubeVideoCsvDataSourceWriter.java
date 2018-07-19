package by.wgdetective.information.resolver.core.service.writer;

import by.wgdetective.information.resolver.core.config.YoutubeVideoInfoCsvConfig;
import by.wgdetective.information.resolver.core.model.response.YoutubeVideoResponse;

import java.io.File;

/**
 * @author Wladimir Litvinov
 */
public class YoutubeVideoCsvDataSourceWriter extends ResponseCsvDataSourceWriter<YoutubeVideoResponse> {

    public YoutubeVideoCsvDataSourceWriter(final File outputCsvFile) {
        super(outputCsvFile, YoutubeVideoInfoCsvConfig.LINE_DELIMITER);
    }
}
