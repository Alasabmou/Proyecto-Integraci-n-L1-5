package aiss.peertubeminer.service;

import aiss.peertubeminer.model.peertube.CaptionSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CaptionServiceTest {

    @Autowired
    CaptionService captionService;

    @Test
    void testGetCaptions() {
        String videoId = "4F8xmc46s2Amx5HzEqfFZm"; 

        CaptionSearch result = captionService.getCaptions(videoId);

        assertNotNull(result, "El objeto CaptionSearch no debería ser nulo");
        assertNotNull(result.getData(), "La lista de subtítulos (data) no debería ser nula");
        
        System.out.println("Se han encontrado " + result.getData().size() + " idiomas de subtítulos para el vídeo.");
    }
}