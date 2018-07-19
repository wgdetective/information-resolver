package by.wgdetective.information.resolver.shell;

import by.wgdetective.information.resolver.core.ResolverMode;
import by.wgdetective.information.resolver.core.config.AcmpTasksConfig;
import by.wgdetective.information.resolver.core.service.extractor.KPLinkDataExtractor;
import by.wgdetective.information.resolver.core.service.resolver.AcmpTasksCsvResolver;
import by.wgdetective.information.resolver.core.service.resolver.KPLinkCsvResolver;
import by.wgdetective.information.resolver.core.service.resolver.YoutubeVideoCsvDataResolver;
import com.sun.security.ntlm.Client;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Wladimir Litvinov
 */
@ShellComponent
public class ResolveCommand {
    private static final Logger LOGGER = Logger.getLogger(ResolveCommand.class.getName());

    @ShellMethod("Resolve information")
    public String resolve(@ShellOption({"-m", "--mode"}) final ResolverMode resolverMode,
                          @ShellOption(value = {"-i", "--inputFile"},
                                  defaultValue = "input.csv") final File inputFile,
                          @ShellOption(value = {"-o", "--outputFile"},
                                  defaultValue = "output.csv") final File outputFile)
            throws IOException {
        return process(resolverMode, inputFile, outputFile);
    }

    private String process(final ResolverMode resolverMode, final File inputFile, final File outputFile)
            throws IOException {
        switch (resolverMode) {
            case KP_LINK:
                new KPLinkCsvResolver(inputFile, outputFile)
                        .resolve(Collections.singletonMap(KPLinkDataExtractor.NO_IMAGES_MODE, true));
                break;
            case YOUTUBE_VIDEO_INFO:
                new YoutubeVideoCsvDataResolver(inputFile, outputFile).resolve(Collections.emptyMap());
                break;
            case ACMP_TASKS:
                return "Use resolve-acmp command";
        }
        return "Done";
    }

    //resolve-acmp-tasks -i ../../Downloads/Первый\ этап\ курсов\ GP\ (Ответы)\ -\ Ответы\ на\ форму\ (1).csv -o ../acmp_out.csv -r 579,670,278,557
    @ShellMethod("Resolve solved acmp tasks")
    public String resolveAcmpTasks(@ShellOption(value = {"-i",
                                                         "--inputFile"}, defaultValue = "input.csv") final File inputFile,
                                   @ShellOption(value = {"-o",
                                                         "--outputFile"}, defaultValue = "output.csv") final File outputFile,
                                   @ShellOption(value = {"-r", "--requiredTasks"}) final List<Long> requiredTasks)
            throws IOException {
        new AcmpTasksCsvResolver(inputFile, outputFile)
                .resolve(Collections.singletonMap(AcmpTasksConfig.REQUIRED_TASKS, requiredTasks));
        return "Done";
    }

}
