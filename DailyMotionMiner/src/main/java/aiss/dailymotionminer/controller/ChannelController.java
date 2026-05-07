package aiss.dailymotionminer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionminer.model.dailymotion.DmChannel;
import aiss.dailymotionminer.model.dailymotion.DmUser;
import aiss.dailymotionminer.model.dailymotion.DmVideos;
import aiss.dailymotionminer.model.videominer.VMChannel;
import aiss.dailymotionminer.service.ChannelService;
import aiss.dailymotionminer.service.UserService;
import aiss.dailymotionminer.service.VideoService;

import aiss.dailymotionminer.etl.Transformer;

@RestController
@RequestMapping("/api/dailymotion")
public class ChannelController {
    
    @Autowired
    ChannelService channelService;
    @Autowired
    VideoService videoService;
    @Autowired
    UserService userService;
    @Autowired
    Transformer transformer;

    @Autowired
    RestTemplate restTemplate;
    
    @Value("${dailymotion.maxVideos}")
    private String defaultMaxVideos;
    
    @Value("${dailymotion.maxPages}")
    private String defaultMaxPages;

    @Value("${videominer.uri}")
    private String videominerUri;

    //GET http://localhost:8081/api/dailymotion/{id}
    @GetMapping("/{id}")
    public VMChannel findOneChannel(@PathVariable String id,
        @RequestParam(required = false) String maxVideos,
        @RequestParam(required = false) String maxPages) {

        String max_videos = maxVideos != null ? maxVideos : defaultMaxVideos;
        String max_pages = maxPages != null ? maxPages : defaultMaxPages;
        
        DmChannel dmChannel = channelService.getChannel(id);
        DmVideos videos = videoService.getVideos(id, Integer.valueOf(max_videos), Integer.valueOf(max_pages));
        DmUser user = userService.getUser(id);
        
        VMChannel vmChannel = transformer.transformChannel(dmChannel, videos, user);

        return vmChannel;
    }

    @PostMapping("/{id}")
    public VMChannel postChannel(@PathVariable String id,
        @RequestParam(required = false) String maxVideos,
        @RequestParam(required = false) String maxPages) {

            String max_videos = maxVideos != null ? maxVideos : defaultMaxVideos;
            String max_pages = maxPages != null ? maxPages : defaultMaxPages;

            VMChannel _channel = this.findOneChannel(id, max_videos, max_pages);

                ResponseEntity<VMChannel> response = restTemplate.postForEntity(videominerUri, _channel, VMChannel.class); 
                System.out.println("Respuesta de VideoMiner: " + response.getStatusCode());
            

            return _channel;
        }
}
