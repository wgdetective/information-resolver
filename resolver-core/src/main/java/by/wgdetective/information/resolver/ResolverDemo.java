package by.wgdetective.information.resolver;

import by.wgdetective.information.resolver.core.ResolverMode;
import by.wgdetective.information.resolver.core.service.extractor.KPLinkDataExtractor;
import by.wgdetective.information.resolver.core.service.resolver.KPLinkCsvResolver;
import by.wgdetective.information.resolver.core.service.resolver.YoutubeVideoCsvDataResolver;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Wladimir Litvinov
 */
public class ResolverDemo {
    private static final Logger LOGGER = Logger.getLogger(ResolverDemo.class.getName());

    public static void main(final String[] args) throws IOException, InterruptedException {
        if (args != null || args.length > 0) {
            final ResolverMode resolverMode = ResolverMode.getByShortName(args[0]);
            assert resolverMode != null;

            final String inputPath = args.length > 1 ? args[1] : "input.csv";
            final String outputPath = args.length > 2 ? args[2] : "output.csv";

            LOGGER.log(Level.INFO, "inputPath=" + inputPath);
            LOGGER.log(Level.INFO, "outputPath=" + outputPath);

            process(resolverMode, new File(inputPath), new File(outputPath));
        }
        LOGGER.log(Level.INFO, "Done");
    }

    private static void process(final ResolverMode resolverMode, final File inputPath, final File outputPath)
            throws IOException {
        switch (resolverMode) {
            case KP_LINK:
                new KPLinkCsvResolver(inputPath, outputPath)
                        .resolve(Collections.singletonMap(KPLinkDataExtractor.NO_IMAGES_MODE, true));
                break;
            case YOUTUBE_VIDEO_INFO:
                new YoutubeVideoCsvDataResolver(inputPath, outputPath).resolve(Collections.emptyMap());
                break;
        }
    }
}
