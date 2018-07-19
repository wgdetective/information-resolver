package by.wgdetective.information.resolver.core.factory;

import by.wgdetective.information.resolver.core.model.request.AcmpTasksRequest;

/**
 * @author Wladimir Litvinov
 */
public class AcmpTasksRequestFactory implements RequestFromArrayFactory<AcmpTasksRequest> {
    @Override
    public AcmpTasksRequest create(final String[] array) {
        return new AcmpTasksRequest(array[0], array[1], Long.parseLong(array[2]));
    }
}
