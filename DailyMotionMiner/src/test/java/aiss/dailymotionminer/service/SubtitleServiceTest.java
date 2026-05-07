package aiss.dailymotionminer.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.dailymotionminer.model.dailymotion.DmSubtitles;

@SpringBootTest
public class SubtitleServiceTest {
    
    @Autowired
    SubtitleService subtitleService;

    @Test
    @DisplayName("Test de obtener los subtitulos a partir de un video")
    void testGetSubtitles() {
        DmSubtitles subtitles = subtitleService.getSubtitles("x84sh87");
        assertNotNull(subtitles);
        System.out.println(subtitles);
    }
}
