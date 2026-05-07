package aiss.dailymotionminer.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.dailymotionminer.model.dailymotion.DmVideos;

@SpringBootTest
public class VideoServiceTest {

    @Autowired
    VideoService videoService;

    @Test
    @DisplayName("Test de obtener los videos indicando maxPages y masVideos")
    void testGetVideos() {
        DmVideos videos = videoService.getVideos("shortfilms", 2, 1);
        assertNotNull(videos);
        System.out.println(videos);
    }
    
    
}
