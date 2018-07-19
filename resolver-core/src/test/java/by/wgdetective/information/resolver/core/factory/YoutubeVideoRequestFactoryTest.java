package by.wgdetective.information.resolver.core.factory;

import by.wgdetective.information.resolver.core.model.request.YoutubeVideoRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Wladimir Litvinov
 */
public class YoutubeVideoRequestFactoryTest {
    private YoutubeVideoRequestFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new YoutubeVideoRequestFactory();
    }

    @Test
    public void createFromVideoId() {
        final YoutubeVideoRequest request =
                factory.create(new String[]{"JGRAtRzGWlw"});
        assertEquals("JGRAtRzGWlw", request.getVideoId());
    }

    @Test
    public void createFromLink() {
        final YoutubeVideoRequest request =
                factory.create(new String[]{"https://www.youtube.com/watch?v=JGRAtRzGWlw"});
        assertEquals("JGRAtRzGWlw", request.getVideoId());
    }

    @Test
    public void createFromLinkWithTime() {
        final YoutubeVideoRequest request =
                factory.create(new String[]{"https://www.youtube.com/watch?v=JGRAtRzGWlw&t=480s"});
        assertEquals("JGRAtRzGWlw", request.getVideoId());
    }

}