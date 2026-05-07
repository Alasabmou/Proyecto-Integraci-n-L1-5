package aiss.peertubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeminer.model.peertube.VideoSearch;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${peertubeminer.baseuri}")
    private String baseUri;


    public VideoSearch getVideos(String channelId, Integer maxVideos) {
                String uri = baseUri + "/video-channels/" + channelId + "/videos?count=" + maxVideos;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<VideoSearch> response = restTemplate.exchange(uri, HttpMethod.GET, entity, VideoSearch.class);
        return response.getBody();
    }
}