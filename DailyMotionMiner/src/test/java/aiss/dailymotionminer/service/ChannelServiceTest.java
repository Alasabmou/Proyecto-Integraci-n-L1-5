package aiss.dailymotionminer.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.dailymotionminer.model.dailymotion.DmChannel;

@SpringBootTest
public class ChannelServiceTest {
    
    @Autowired
    ChannelService channelService;

    @Test
    @DisplayName("Test de obtener un canal")
    void testGetChannel() {
        DmChannel canal = channelService.getChannel("shortfilms");
        assertNotNull(canal);
        System.out.println(canal.toString());
    }
}
