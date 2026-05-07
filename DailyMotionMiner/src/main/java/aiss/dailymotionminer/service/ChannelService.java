package aiss.dailymotionminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionminer.model.dailymotion.DmChannel;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.baseuri}")
    private String baseUri;


    public DmChannel getChannel(String name) {
        String uri = baseUri +"user/" + name + "?fields=id,username,description,created_time";
        DmChannel canal = restTemplate.getForObject(uri, DmChannel.class);
        System.out.println(canal.toString());
        return canal;
    }
    
}
