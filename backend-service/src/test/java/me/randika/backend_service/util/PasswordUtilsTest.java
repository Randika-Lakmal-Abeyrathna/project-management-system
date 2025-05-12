package me.randika.backend_service.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;

class PasswordUtilsTest {

    @Test
    void testHashPassword() throws NoSuchAlgorithmException {
        // Given
        String password = "testPassword123";
        
        // When
        String hashedPassword = PasswordUtils.hashPassword(password);
        
        // Then
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
        
        // Hash should be consistent
        String hashedAgain = PasswordUtils.hashPassword(password);
        assertEquals(hashedPassword, hashedAgain);
    }
    
    @Test
    void testVerifyPassword() throws NoSuchAlgorithmException {
        // Given
        String password = "testPassword123";
        String hashedPassword = PasswordUtils.hashPassword(password);
        
        // When & Then
        assertTrue(PasswordUtils.verifyPassword(password, hashedPassword));
        assertFalse(PasswordUtils.verifyPassword("wrongPassword", hashedPassword));
    }
}