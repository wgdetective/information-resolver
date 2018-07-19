package by.wgdetective.information.resolver.core.service.extractor;

import java.util.List;
import java.util.Map;

/**
 * @author Wladimir Litvinov
 */
public interface RemoteDataExtractor<REQUEST_TYPE, RESPONSE_TYPE> {

    List<RESPONSE_TYPE> extract(final List<REQUEST_TYPE> requestList, final Map<String, Object> params);
}
