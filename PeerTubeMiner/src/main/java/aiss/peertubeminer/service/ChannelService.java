package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Channel; // O el nombre de tu POJO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${peertubeminer.baseuri}")
    private String baseUri;

    public Channel getChannel(String channelHandle) {
        String uri = baseUri + "/video-channels/" + channelHandle;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Channel.class);
        return response.getBody();
    }
}