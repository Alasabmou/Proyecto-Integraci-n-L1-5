package aiss.peertubeminer.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.peertubeminer.model.peertube.CommentSearch;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;


    @Test
    void testGetCommentsWithLimit() {
        String videoId = "fpSV5vwy47N3xUbxi2tmBX"; 
        Integer limiteComments = 5;
        
        CommentSearch result = commentService.getComments(videoId, limiteComments);

        assertNotNull(result, "El sobre no debería ser nulo");
        assertNotNull(result.getData(), "La lista de comentarios no debería ser nula");
        
        System.out.println("Límite forzado a " + limiteComments + ". Encontrados: " + result.getData().size());
    }
}