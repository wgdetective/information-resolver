package by.wgdetective.information.resolver.core.service.writer;

import by.wgdetective.information.resolver.core.config.AcmpTasksConfig;
import by.wgdetective.information.resolver.core.model.response.AcmpTasksResponse;

import java.io.File;

/**
 * @author Wladimir Litvinov
 */
public class AcmpTasksCsvDataSourceWriter extends ResponseCsvDataSourceWriter<AcmpTasksResponse> {

    public AcmpTasksCsvDataSourceWriter(final File outputCsvFile) {
        super(outputCsvFile, AcmpTasksConfig.LINE_DELIMITER);
    }
}