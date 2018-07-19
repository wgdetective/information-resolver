package by.wgdetective.information.resolver.core.service.resolver;

import by.wgdetective.information.resolver.core.service.extractor.RemoteDataExtractor;
import by.wgdetective.information.resolver.core.service.reader.RequestDataSourceReader;
import by.wgdetective.information.resolver.core.service.writer.ResponseDataSourceWriter;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Wladimir Litvinov
 */
@Data
public abstract class AbstractInformationResolver<REQUEST_TYPE, RESPONSE_TYPE> implements InformationResolver {

    protected final RequestDataSourceReader<REQUEST_TYPE> requestDataSourceReader;
    protected final ResponseDataSourceWriter<RESPONSE_TYPE> responseDataSourceWriter;

    protected final RemoteDataExtractor<REQUEST_TYPE, RESPONSE_TYPE> remoteDataExtractor;


    public AbstractInformationResolver(final RequestDataSourceReader<REQUEST_TYPE> requestDataSourceReader,
                                       final ResponseDataSourceWriter<RESPONSE_TYPE> responseDataSourceWriter,
                                       final RemoteDataExtractor<REQUEST_TYPE, RESPONSE_TYPE> remoteDataExtractor) {
        this.requestDataSourceReader = requestDataSourceReader;
        this.responseDataSourceWriter = responseDataSourceWriter;
        this.remoteDataExtractor = remoteDataExtractor;
    }

    @Override
    public void resolve(final Map<String, Object> params) throws IOException {
        final List<REQUEST_TYPE> requestList = requestDataSourceReader.read();
        final List<RESPONSE_TYPE> responseList = remoteDataExtractor.extract(requestList, params);
        responseDataSourceWriter.writeDataResponses(responseList);
    }
}
