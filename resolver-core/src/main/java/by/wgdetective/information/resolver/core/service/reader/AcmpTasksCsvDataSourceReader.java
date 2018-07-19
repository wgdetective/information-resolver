package by.wgdetective.information.resolver.core.service.reader;

import by.wgdetective.information.resolver.core.config.AcmpTasksConfig;
import by.wgdetective.information.resolver.core.factory.AcmpTasksRequestFactory;
import by.wgdetective.information.resolver.core.model.request.AcmpTasksRequest;

import java.io.File;

/**
 * @author Wladimir Litvinov
 */
public class AcmpTasksCsvDataSourceReader extends RequestCsvDataSourceReader<AcmpTasksRequest> {

    public AcmpTasksCsvDataSourceReader(final File inputCsvFile) {
        super(inputCsvFile, AcmpTasksConfig.INPUT_FIELD_DELIMITER, new AcmpTasksRequestFactory());
    }
}
