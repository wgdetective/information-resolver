package by.wgdetective.information.resolver.core.service.extractor;

import by.wgdetective.information.resolver.core.config.YoutubeVideoInfoCsvConfig;
import by.wgdetective.information.resolver.core.model.request.YoutubeVideoRequest;
import by.wgdetective.information.resolver.core.model.response.YoutubeVideoResponse;
import by.wgdetective.information.resolver.core.util.FormatUtil;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Wladimir Litvinov
 */
public class YoutubeVideoDataExtractor implements RemoteDataExtractor<YoutubeVideoRequest, YoutubeVideoResponse> {
    private static final Logger LOGGER = Logger.getLogger(YoutubeVideoDataExtractor.class.getName());

    private final YouTube youtube;

    public YoutubeVideoDataExtractor() {
        this.youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(),
                                           request -> {}).setApplicationName("video-test").build();
    }

    @Override
    public List<YoutubeVideoResponse> extract(final List<YoutubeVideoRequest> requestList,
                                              final Map<String, Object> params) {
        final List<YoutubeVideoResponse> result = new ArrayList<>(requestList.size());
        for (YoutubeVideoRequest request : requestList) {

            try {
                final YouTube.Videos.List videoRequest = formVideoRequest(request);
                final VideoListResponse listResponse = videoRequest.execute();

                final List<Video> videoList = listResponse.getItems();
                final Video targetVideo = videoList.iterator().next();

                final String title = targetVideo.getSnippet().getTitle();
                final String author = targetVideo.getSnippet().getChannelTitle();
                final String link = YoutubeVideoInfoCsvConfig.LINK_PREFIX + request.getVideoId();
                final Duration duration = Duration
                        .parse(targetVideo.getContentDetails().getDuration());

                result.add(
                        new YoutubeVideoResponse(title, author, link, FormatUtil.formatDuration(duration),
                                                 request.getDate(), request.getRate()));
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        return result;
    }

    private YouTube.Videos.List formVideoRequest(final YoutubeVideoRequest request) throws IOException {
        final YouTube.Videos.List videoRequest = youtube.videos()
                                                        .list("snippet,statistics,contentDetails");
        videoRequest.setId(request.getVideoId());
        videoRequest.setKey(YoutubeVideoInfoCsvConfig.API_KEY);
        return videoRequest;
    }
}
