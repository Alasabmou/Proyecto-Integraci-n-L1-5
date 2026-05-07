package aiss.peertubeminer.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc // Esto levanta el "Postman interno"
class ChannelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/peertube/{id} - Debe devolver el canal traducido")
    void shouldReturnChannel() throws Exception {
        String channelId = "framaconf";
        
        // Simulamos la petición GET
        mockMvc.perform(get("/api/peertube/" + channelId)
                .param("maxVideos", "2")
                .param("maxComments", "1"))
                .andExpect(status().isOk()) // Comprobar que devuelve 200 OK
                .andExpect(jsonPath("$.id").exists()) // Comprobar que el JSON tiene un campo ID
                .andExpect(jsonPath("$.videos").isArray()) // Comprobar que hay una lista de vídeos
                .andExpect(jsonPath("$.videos[0].name").exists()); // Comprobar que el primer vídeo tiene nombre
        
        System.out.println("✅ TEST CONTROLADOR: La API responde correctamente y devuelve JSON estructurado.");
    }
}