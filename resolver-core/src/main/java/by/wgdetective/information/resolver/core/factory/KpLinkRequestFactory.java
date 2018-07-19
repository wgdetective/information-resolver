package by.wgdetective.information.resolver.core.factory;

import by.wgdetective.information.resolver.core.config.KPLinkCsvConfig;
import by.wgdetective.information.resolver.core.model.request.KPLinkRequest;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * @author Wladimir Litvinov
 */
public class KpLinkRequestFactory implements RequestFromArrayFactory<KPLinkRequest> {

    @Override
    public KPLinkRequest create(final String[] array) {
        final String link = array[0];
        final LocalDate viewDate =
                array.length < 2 || StringUtils.isEmpty(array[1]) ? LocalDate.now()
                                              : LocalDate.parse(array[1], KPLinkCsvConfig.DATE_TIME_FORMATTER);
        final Long rate =
                array.length > 2 && StringUtils.isNoneEmpty(array[2]) ? Long.parseLong(array[2])
                                                                      : null;
        return new KPLinkRequest(link, viewDate, rate);
    }
}
