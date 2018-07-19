package by.wgdetective.information.resolver.core.util;

import java.time.Duration;

/**
 * @author Wladimir Litvinov
 */
public class FormatUtil {
    private FormatUtil() {}

    public static String formatDuration(final Duration duration) {
        final long durationInSec = duration.getSeconds();
        final long hours = durationInSec / 3600;
        final long minutes = durationInSec % 3600 / 60;
        final long seconds = durationInSec % 60;
        final String formatted = formatTimeUnit(minutes) + ":" + formatTimeUnit(seconds);
        if (hours > 0) {
            return hours + ":" + formatted;
        } else {
            return formatted;
        }
    }

    public static String formatTimeUnit(final long value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }
}
