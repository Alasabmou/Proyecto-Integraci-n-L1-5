package aiss.dailymotionminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionminer.model.dailymotion.DmSubtitles;

@Service
public class SubtitleService {
 
    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.baseuri}")
    private String baseUri;

    public DmSubtitles getSubtitles(String videoId) {
        String uri = baseUri + "video/" + videoId + "/subtitles?fields=id,url,language";
        DmSubtitles subtitles = restTemplate.getForObject(uri, DmSubtitles.class);
        System.out.println(subtitles.toString());
        return subtitles;
    }
}
