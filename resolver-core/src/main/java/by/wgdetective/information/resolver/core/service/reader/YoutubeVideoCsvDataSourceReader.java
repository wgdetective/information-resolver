package by.wgdetective.information.resolver.core.service.reader;

import by.wgdetective.information.resolver.core.config.YoutubeVideoInfoCsvConfig;
import by.wgdetective.information.resolver.core.factory.YoutubeVideoRequestFactory;
import by.wgdetective.information.resolver.core.model.request.YoutubeVideoRequest;

import java.io.File;


/**
 * @author Wladimir Litvinov
 */
public class YoutubeVideoCsvDataSourceReader extends RequestCsvDataSourceReader<YoutubeVideoRequest> {

    public YoutubeVideoCsvDataSourceReader(final File inputCsvFile) {
        super(inputCsvFile, YoutubeVideoInfoCsvConfig.INPUT_FIELD_DELIMITER, new YoutubeVideoRequestFactory());
    }
}
