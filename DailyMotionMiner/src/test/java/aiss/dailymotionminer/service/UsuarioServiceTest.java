package aiss.dailymotionminer.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aiss.dailymotionminer.model.dailymotion.DmUser;

@SpringBootTest
public class UsuarioServiceTest {
    
    @Autowired
    UserService userService;

    @Test
    @DisplayName("Test de obtener el usuario de un canal") 
    void testGetUser() {
        DmUser usuario = userService.getUser("shortfilms");
        assertNotNull(usuario);
        System.out.println(usuario);
    }
}
