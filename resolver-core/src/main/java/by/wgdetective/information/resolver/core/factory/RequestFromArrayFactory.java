package by.wgdetective.information.resolver.core.factory;

/**
 * @author Wladimir Litvinov
 */
public interface RequestFromArrayFactory<Request> {
    Request create(final String[] array);
}
