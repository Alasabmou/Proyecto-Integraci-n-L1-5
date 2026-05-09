package aiss.dailymotionminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionminer.model.dailymotion.DmVideos;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.baseuri}")
    private String baseUri;

    public DmVideos getVideos(String channel, Integer maxVideos, Integer maxPages) {    
        String uri = baseUri +"videos?channel=" + channel + "&limit=" + maxVideos + "&page=1&fields=id,title,description,created_time,tags";
        DmVideos videos = restTemplate.getForObject(uri, DmVideos.class);
        if (maxPages > 1) {
            for (int i = 2; i <= maxPages; i++) {
                String uriAux = baseUri +"videos?channel=" + channel + "&limit=" + maxVideos + "&page=" + i +"&fields=id,title,description,created_time,tags";
                DmVideos videosAux = restTemplate.getForObject(uriAux, DmVideos.class);
                videos.getList().addAll(videosAux.getList());
            }
        }
        System.out.println(videos.toString());
        return videos;
    }

    
    
}
