package by.wgdetective.information.resolver.core.factory;

import by.wgdetective.information.resolver.core.model.request.AcmpTasksRequest;

/**
 * @author Wladimir Litvinov
 */
public class AcmpTasksRequestFactory implements RequestFromArrayFactory<AcmpTasksRequest> {
    @Override
    public AcmpTasksRequest create(final String[] array) {
        final int idIndex = array[2].indexOf("id=");
        final Long acmpId = idIndex != -1 ? Long.parseLong(array[2].substring(idIndex + 3).replace(" ", "")) : null;
        return new AcmpTasksRequest(null, array[0], acmpId);
    }
}
