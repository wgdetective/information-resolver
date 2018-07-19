package by.wgdetective.information.resolver.core.service.writer;

import by.wgdetective.information.resolver.core.config.KPLinkCsvConfig;
import by.wgdetective.information.resolver.core.model.response.KPLinkResponse;

import java.io.File;

/**
 * @author Wladimir Litvinov
 */
public class KPLinkCsvDataSourceWriter extends ResponseCsvDataSourceWriter<KPLinkResponse> {

    public KPLinkCsvDataSourceWriter(final File outputCsvFile) {
        super(outputCsvFile, KPLinkCsvConfig.LINE_DELIMITER);
    }
}
