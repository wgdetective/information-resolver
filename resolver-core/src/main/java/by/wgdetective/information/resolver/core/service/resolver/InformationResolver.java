package by.wgdetective.information.resolver.core.service.resolver;

import java.io.IOException;
import java.util.Map;

/**
 * @author Wladimir Litvinov
 */
public interface InformationResolver {
    void resolve(final Map<String, Object> params) throws IOException;
}
