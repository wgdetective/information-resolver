package by.wgdetective.information.resolver.core.service.resolver;

import by.wgdetective.information.resolver.core.model.request.KPLinkRequest;
import by.wgdetective.information.resolver.core.model.response.KPLinkResponse;
import by.wgdetective.information.resolver.core.service.extractor.KPLinkDataExtractor;
import by.wgdetective.information.resolver.core.service.reader.KPLinkCsvDataSourceReader;
import by.wgdetective.information.resolver.core.service.writer.KPLinkCsvDataSourceWriter;

import java.io.File;

/**
 * @author Wladimir Litvinov
 */
public class KPLinkCsvResolver extends AbstractInformationResolver<KPLinkRequest, KPLinkResponse> {

    public KPLinkCsvResolver(final File inputFile, final File outputFile) {
        super(new KPLinkCsvDataSourceReader(inputFile),
              new KPLinkCsvDataSourceWriter(outputFile), new KPLinkDataExtractor());
    }
}
