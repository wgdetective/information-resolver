package by.wgdetective.information.resolver.core.service.extractor;

import by.wgdetective.information.resolver.core.config.KPLinkCsvConfig;
import by.wgdetective.information.resolver.core.model.request.KPLinkRequest;
import by.wgdetective.information.resolver.core.model.response.KPLinkResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import static by.wgdetective.information.resolver.core.config.KPLinkCsvConfig.FILM_NAME_PATH;
import static by.wgdetective.information.resolver.core.config.KPLinkCsvConfig.SCRIPTS_TIMEOUT;

/**
 * @author Wladimir Litvinov
 */
public class KPLinkDataExtractor implements RemoteDataExtractor<KPLinkRequest, KPLinkResponse> {

    public static final String NO_IMAGES_MODE = "noImagesMode";

    //todo all to xpath
    @Override
    public List<KPLinkResponse> extract(final List<KPLinkRequest> requestList,
                                        final Map<String, Object> params) {

        final boolean noImagesMode = (boolean) params.getOrDefault(NO_IMAGES_MODE, false);

        final WebDriver driver = initWebDriver();
        final List<KPLinkResponse> result = new ArrayList<>(requestList.size());
        try {
            for (KPLinkRequest request : requestList) {
                driver.navigate().to(request.getLink());

                final List<WebElement> infoElements = driver.findElements(By.className("type"));

                final String name = getFilmName(driver);
                final String year = getYear(infoElements);
                final String countries = getCountries(infoElements, noImagesMode);
                final String director = getDirector(infoElements);
                final String actors = getActors(driver, noImagesMode);
                final String kpRate = getKPRate(driver);

                result.add(new KPLinkResponse(name, director, year, countries,
                                              actors, kpRate,
                                              request.getDate(),
                                              request.getRate()));
            }

        } finally {
            driver.close();
            driver.quit();
        }
        return result;
    }

    private WebDriver initWebDriver() {
        final WebDriver driver = new SafariDriver();
        driver.manage().timeouts().setScriptTimeout(SCRIPTS_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }

    private String getFilmName(final WebDriver driver) {
        return driver.findElement(By.xpath(FILM_NAME_PATH))
                     .getText();
    }

    private String getYear(final List<WebElement> infoElements) {
        return findInfoValueElementByLabel(infoElements, KPLinkCsvConfig.LABEL_YEAR,
                                           KPLinkCsvConfig.COMMON_INFO_PATH).getText();
    }

    private String getDirector(final List<WebElement> infoElements) {
        return findInfoValueElementByLabel(infoElements, KPLinkCsvConfig.LABEL_DIRECTOR,
                                           KPLinkCsvConfig.DIRECTOR_INFO_PATH).getText();
    }

    private String getActors(final WebDriver driver, final boolean noImagesMode) {
        final StringJoiner actors = new StringJoiner(", ");
        if (noImagesMode) {
            final List<WebElement> actorsElements =
                    noImagesMode ? driver
                            .findElement(By.cssSelector("#actorList > ul:nth-child(2)"))
                            .findElements(By.tagName("a")) :
                    driver.findElements(By.cssSelector("#actorList > ul:nth-child(2)"));
            actorsElements.stream().limit(KPLinkCsvConfig.MAX_ACTORS)
                          .forEach(e -> actors.add(e.getText()));
        } else {
            final List<WebElement> actorsElements = driver
                    .findElements(By.cssSelector("#actorList > ul:nth-child(2)"));
            actorsElements.stream().limit(KPLinkCsvConfig.MAX_ACTORS)
                          .forEach(e -> actors.add(e.findElement(By.tagName("a")).getText()));
        }
        return actors.toString();
    }

    private String getCountries(final List<WebElement> infoElements, final boolean noImagesMode) {
        final String path = noImagesMode ? KPLinkCsvConfig.NO_IMAGES_YEAR
                                         : KPLinkCsvConfig.COMMON_INFO_PATH;
        final List<WebElement> countryElements =
                findInfoValueElementByLabel(infoElements, KPLinkCsvConfig.LABEL_COUNTRY, path)
                        .findElements(By.tagName("a"));
        final StringJoiner countries = new StringJoiner(", ");
        countryElements.forEach(e -> countries.add(e.getText()));
        return countries.toString();
    }

    private String getKPRate(final WebDriver driver) {
        return driver.findElement(By.cssSelector(
                ".rating_ball")).getText();
    }


    private WebElement findInfoValueElementByLabel(final List<WebElement> infoElements,
                                                   final String label,
                                                   final String contentXPath) {
        for (WebElement infoElement : infoElements) {
            if (infoElement.getText().equals(label)) {
                final WebElement parent = infoElement.findElement(By.xpath(".."));
                return parent.findElement(By.xpath(contentXPath));
            }
        }
        return null;
    }
}
