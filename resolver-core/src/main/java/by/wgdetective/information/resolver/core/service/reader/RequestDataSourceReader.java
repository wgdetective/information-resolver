package by.wgdetective.information.resolver.core.service.reader;

import java.io.IOException;
import java.util.List;

/**
 * @author Wladimir Litvinov
 */
public interface RequestDataSourceReader<REQUEST_TYPE> {

    List<REQUEST_TYPE> read() throws IOException;
}
