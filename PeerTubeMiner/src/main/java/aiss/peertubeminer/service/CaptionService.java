package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.CaptionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${peertubeminer.baseuri}")
    private String baseUri;

    public CaptionSearch getCaptions(String videoId) {
        String uri = baseUri + "/videos/" + videoId + "/captions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<CaptionSearch> response = restTemplate.exchange(uri, HttpMethod.GET, entity, CaptionSearch.class);
        CaptionSearch captions = response.getBody();

        if (captions != null) {
            System.out.println("Subtítulos encontrados: " + captions.toString());
        }

        return captions;
    }
}