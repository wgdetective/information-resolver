package by.wgdetective.information.resolver.core.service.extractor;

import by.wgdetective.information.resolver.core.config.AcmpTasksConfig;
import by.wgdetective.information.resolver.core.model.request.AcmpTasksRequest;
import by.wgdetective.information.resolver.core.model.response.AcmpTasksResponse;
import by.wgdetective.information.resolver.core.model.response.AcmpVerdict;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import javax.crypto.AEADBadTagException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Wladimir Litvinov
 */
public class AcmpTasksDataExtractor implements RemoteDataExtractor<AcmpTasksRequest, AcmpTasksResponse> {
    public static final Logger LOGGER = Logger.getLogger(AEADBadTagException.class.getName());


    //todo generify - init and process one by one
    @Override
    public List<AcmpTasksResponse> extract(final List<AcmpTasksRequest> requestList,
                                           final Map<String, Object> params) {
        final WebDriver driver = new SafariDriver();
        final List<AcmpTasksResponse> result = new ArrayList<>(requestList.size());
        final List<Long> requiredTasks = (List<Long>) params.get(AcmpTasksConfig.REQUIRED_TASKS);
        for (AcmpTasksRequest request : requestList) {
            try {
                driver.navigate().to(AcmpTasksConfig.LINK_PREFIX + request.getAcmpId());
                final WebElement element = driver.findElement(By.xpath(AcmpTasksConfig.SOLVED_TASKS_LINK));
                final String tasks = element.getText().replace("\n", "");

                final List<Long> tasksList = Arrays.stream(tasks.split(" "))
                                                   .filter(t -> !t.isEmpty())
                                                   .map(Long::parseLong).collect(Collectors.toList());
                final AcmpVerdict verdict = check(tasksList, requiredTasks);
                result.add(new AcmpTasksResponse(request.getAcmpId(), tasksList, verdict));
            } catch (final NoSuchElementException e) {
                result.add(new AcmpTasksResponse(request.getAcmpId(), Collections.emptyList(),
                                                 AcmpVerdict.PROFILE_NOT_FOUND));
            }
        }
        return result;
    }

    private AcmpVerdict check(final List<Long> tasksList, final List<Long> requiredTasks) {
        for (final Long requiredTask : requiredTasks) {
            if (!tasksList.contains(requiredTask)) {
                return AcmpVerdict.TASKS_NOT_SOLVED;
            }
        }
        return AcmpVerdict.TASKS_SOLVED;
    }
}
