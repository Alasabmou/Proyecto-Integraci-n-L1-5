package aiss.peertubeminer.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.peertubeminer.model.peertube.VideoSearch;

@SpringBootTest
class VideoServiceTest {

    @Autowired
    VideoService videoService;

    
    @Test
    void testGetVideosWithLimit() {
        String channelHandle = "non_frama";
        Integer limiteForzado = 3;
        
        VideoSearch result = videoService.getVideos(channelHandle, limiteForzado);

        assertNotNull(result, "El sobre VideoSearch no debería ser nulo");
        assertNotNull(result.getData(), "La lista de vídeos no debería ser nula");
        
        assertTrue(result.getData().size() <= limiteForzado, "¡Error! Ha devuelto más vídeos de los permitidos.");
        
        System.out.println("Lista de videos con límite de " + limiteForzado + ". Encontrados: " + result.getData().size());
    }
}