package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.Channel; // O tu clase de usuario/canal
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {

    @Autowired
    ChannelService channelService;

    @Test
    void testGetChannel() {
        String channelHandle = "non_frama";

        Channel result = channelService.getChannel(channelHandle);

        assertNotNull(result, "El canal no debería ser nulo");
        System.out.println("Canal encontrado: " + result.toString());
    }
}