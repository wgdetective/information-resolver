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
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static by.wgdetective.information.resolver.core.model.response.AcmpVerdict.TASKS_SOLVED;

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
        for (final AcmpTasksRequest request : requestList) {
            if (request.getAcmpId() != null) {
                try {
                    driver.navigate().to(AcmpTasksConfig.LINK_PREFIX + request.getAcmpId());
                    final WebElement element = driver.findElement(By.xpath(AcmpTasksConfig.SOLVED_TASKS_LINK));
                    final String tasks = element.getText().replace("\n", "");

                    final List<Long> tasksList = Arrays.stream(tasks.split(" "))
                        .filter(t -> !t.isEmpty())
                        .map(Long::parseLong).collect(Collectors.toList());
                    final int solvedTasksCount = check(tasksList, requiredTasks);
                    final AcmpVerdict verdict = solvedTasksCount == requiredTasks.size()
                                                ? TASKS_SOLVED
                                                : AcmpVerdict.TASKS_NOT_SOLVED;
                    result.add(new AcmpTasksResponse(request.getAcmpId(), tasksList, verdict, solvedTasksCount));
                } catch (final NoSuchElementException e) {
                    result.add(new AcmpTasksResponse(request.getAcmpId(), Collections.emptyList(),
                                                     AcmpVerdict.PROFILE_NOT_FOUND, 0));
                }
            } else {
                result.add(
                    new AcmpTasksResponse(request.getAcmpId(), Collections.emptyList(), AcmpVerdict.TASKS_NOT_SOLVED,
                                          0));
            }
        }
        return result;
    }

    private int check(final List<Long> tasksList, final List<Long> requiredTasks) {
        int result = 0;
        for (final Long requiredTask : requiredTasks) {
            if (tasksList.contains(requiredTask)) {
                result++;
            }
        }
        return result;
    }
}
