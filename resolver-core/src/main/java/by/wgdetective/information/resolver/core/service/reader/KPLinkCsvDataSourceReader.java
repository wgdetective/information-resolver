package by.wgdetective.information.resolver.core.service.reader;

import by.wgdetective.information.resolver.core.config.KPLinkCsvConfig;
import by.wgdetective.information.resolver.core.factory.KpLinkRequestFactory;
import by.wgdetective.information.resolver.core.model.request.KPLinkRequest;

import java.io.File;

/**
 * @author Wladimir Litvinov
 */
public class KPLinkCsvDataSourceReader extends RequestCsvDataSourceReader<KPLinkRequest> {

    public KPLinkCsvDataSourceReader(final File inputCsvFile) {
        super(inputCsvFile, KPLinkCsvConfig.INPUT_FIELD_DELIMITER, new KpLinkRequestFactory());
    }
}
