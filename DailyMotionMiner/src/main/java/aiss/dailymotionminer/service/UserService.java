package aiss.dailymotionminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionminer.model.dailymotion.DmUser;

@Service
public class UserService {
 
    @Autowired
    RestTemplate restTemplate;

    @Value("${dailymotion.baseuri}")
    private String baseUri;

    public DmUser getUser(String channel) {
        String uri = baseUri +"user/" + channel + "?fields=id,screenname,url,avatar_720_url";
        DmUser usuario = restTemplate.getForObject(uri, DmUser.class);
        System.out.println(usuario);
        return usuario;
    }

}
