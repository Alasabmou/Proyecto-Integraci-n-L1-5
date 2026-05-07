package aiss.peertubeminer.etl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.service.ChannelService;
import aiss.peertubeminer.service.VideoService;

@SpringBootTest
class TransformerTest {

    @Autowired
    Transformer transformer;

    @Autowired
    ChannelService channelService;

    @Autowired
    VideoService videoService;

    @Test
    void testTransform() {
        String channelId = "framaconf";
        Integer maxVideos = 2;
        Integer maxComments = 1;

        Channel ptChannel = channelService.getChannel(channelId);
        List<Video> ptVideos = videoService.getVideos(channelId, maxVideos).getData();

        VMChannel result = transformer.transform(ptChannel, ptVideos, maxComments);

        assertNotNull(result, "El VMChannel resultante no debería ser nulo");
        assertEquals(ptChannel.getName(), result.getName(), "El nombre del canal debe haberse copiado igual");
        assertEquals(maxVideos, result.getVideos().size(), "El canal debería tener exactamente 2 vídeos anidados");
        
        System.out.println("TEST TRANSFORMER: Canal '" + result.getName() + "' traducido perfectamente con " + result.getVideos().size() + " vídeos.");
    }
}