package by.wgdetective.information.resolver.core;

/**
 * @author Wladimir Litvinov
 */
public enum ResolverMode {
    KP_LINK("kpl"), YOUTUBE_VIDEO_INFO("yvi"), ACMP_TASKS("acmp");

    private final String shortName;

    ResolverMode(final String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static ResolverMode getByShortName(final String shortName) {
        for (ResolverMode resolverMode : ResolverMode.values()) {
            if (resolverMode.getShortName().equals(shortName)) {
                return resolverMode;
            }
        }
        return null;
    }
}
