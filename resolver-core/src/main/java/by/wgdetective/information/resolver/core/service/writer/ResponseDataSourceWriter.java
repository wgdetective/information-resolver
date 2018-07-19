package by.wgdetective.information.resolver.core.service.writer;

import java.io.IOException;
import java.util.List;

/**
 * @author Wladimir Litvinov
 */
public interface ResponseDataSourceWriter<RESPONSE_TYPE> {

    void writeDataResponses(final List<RESPONSE_TYPE> data) throws IOException;
}
