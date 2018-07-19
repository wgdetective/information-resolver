package by.wgdetective.information.resolver.core.service.resolver;

import by.wgdetective.information.resolver.core.model.request.YoutubeVideoRequest;
import by.wgdetective.information.resolver.core.model.response.YoutubeVideoResponse;
import by.wgdetective.information.resolver.core.service.extractor.YoutubeVideoDataExtractor;
import by.wgdetective.information.resolver.core.service.reader.YoutubeVideoCsvDataSourceReader;
import by.wgdetective.information.resolver.core.service.writer.YoutubeVideoCsvDataSourceWriter;

import java.io.File;

/**
 * @author Wladimir Litvinov
 */
public class YoutubeVideoCsvDataResolver
        extends AbstractInformationResolver<YoutubeVideoRequest, YoutubeVideoResponse> {

    public YoutubeVideoCsvDataResolver(final File inputFile, final File outputFile) {
        super(new YoutubeVideoCsvDataSourceReader(inputFile), new YoutubeVideoCsvDataSourceWriter(outputFile),
              new YoutubeVideoDataExtractor());
    }
}
