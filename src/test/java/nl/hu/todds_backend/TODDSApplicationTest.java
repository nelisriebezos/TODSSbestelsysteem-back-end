package nl.hu.todds_backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TODDSApplicationTest {
    @Test
    @DisplayName("Spring context is loaded")
    void contextLoaded() {
        assertTrue(true);
    }
}