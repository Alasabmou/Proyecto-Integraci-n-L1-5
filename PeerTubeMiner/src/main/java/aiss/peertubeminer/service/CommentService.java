package aiss.peertubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeminer.model.peertube.CommentSearch;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${peertubeminer.baseuri}")
    private String baseUri;


    public CommentSearch getComments(String videoId, Integer maxComments) {
        String uri = baseUri + "/videos/" + videoId + "/comment-threads?count=" + maxComments;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CommentSearch> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CommentSearch.class);
        return response.getBody();
    }
}