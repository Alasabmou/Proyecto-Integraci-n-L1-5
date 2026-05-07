package aiss.peertubeminer.controller;

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

import aiss.peertubeminer.etl.Transformer;
import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.VideoSearch;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.service.ChannelService;
import aiss.peertubeminer.service.VideoService;

@RestController
@RequestMapping("/api/peertube") 
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @Autowired
    VideoService videoService;

    @Autowired
    Transformer transformer;

    @Autowired
    RestTemplate restTemplate;

    @Value("${videominer.uri}")
    private String videoMinerUri;


    // GET http://localhost:8082/peertube/{channelId}[?maxVideos=10&maxComments=2]
    @GetMapping("/{channelId}")
    public VMChannel getChannel(
            @PathVariable String channelId,
            @RequestParam(value = "maxVideos", defaultValue = "${peertubeminer.maxVideos}") Integer maxVideos,
            @RequestParam(value = "maxComments", defaultValue = "${peertubeminer.maxComments}") Integer maxComments) {



        Channel ptChannel = channelService.getChannel(channelId);
        VideoSearch ptVideos = videoService.getVideos(channelId, maxVideos);

        VMChannel vmChannel = transformer.transform(ptChannel, ptVideos.getData(), maxComments);

        return vmChannel;
    }


    // POST http://localhost:8082/peertube/{channelId}[?maxVideos=10&maxComments=2]
    @PostMapping("/{channelId}")
    public VMChannel postChannel(
            @PathVariable String channelId,
            @RequestParam(value = "maxVideos", defaultValue = "${peertubeminer.maxVideos}") Integer maxVideos,
            @RequestParam(value = "maxComments", defaultValue = "${peertubeminer.maxComments}") Integer maxComments) {


        VMChannel vmChannel = this.getChannel(channelId, maxVideos, maxComments);

        System.out.println("Enviando a: " + videoMinerUri);
        ResponseEntity<VMChannel> response = restTemplate.postForEntity(videoMinerUri, vmChannel, VMChannel.class);
        
        System.out.println("Respuesta del VideoMiner: " + response.getStatusCode());

        return vmChannel;
    }
}