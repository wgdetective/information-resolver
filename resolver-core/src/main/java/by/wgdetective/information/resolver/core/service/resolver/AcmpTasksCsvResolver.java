package by.wgdetective.information.resolver.core.service.resolver;

import by.wgdetective.information.resolver.core.model.request.AcmpTasksRequest;
import by.wgdetective.information.resolver.core.model.response.AcmpTasksResponse;
import by.wgdetective.information.resolver.core.service.extractor.AcmpTasksDataExtractor;
import by.wgdetective.information.resolver.core.service.reader.AcmpTasksCsvDataSourceReader;
import by.wgdetective.information.resolver.core.service.writer.AcmpTasksCsvDataSourceWriter;

import java.io.File;

/**
 * @author Wladimir Litvinov
 */
public class AcmpTasksCsvResolver extends AbstractInformationResolver<AcmpTasksRequest, AcmpTasksResponse> {

    public AcmpTasksCsvResolver(final File inputFile, final File outputFile) {
        super(new AcmpTasksCsvDataSourceReader(inputFile),
              new AcmpTasksCsvDataSourceWriter(outputFile), new AcmpTasksDataExtractor());
    }
}
