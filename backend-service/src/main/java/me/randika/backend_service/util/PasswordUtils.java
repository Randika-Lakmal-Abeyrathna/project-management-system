package me.randika.backend_service.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Utility class for password operations
 */
public class PasswordUtils {

    private PasswordUtils() {}

    /**
     * Hashes a password using SHA-256 algorithm
     * 
     * @param password The plain text password to hash
     * @return The hashed password
     * @throws NoSuchAlgorithmException if the hashing algorithm is not available
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("Error hashing password", e);
        }
    }

    /**
     * Verifies if a plain text password matches a hashed password
     * 
     * @param plainTextPassword The plain text password to verify
     * @param hashedPassword The hashed password to compare against
     * @return true if the passwords match, false otherwise
     * @throws NoSuchAlgorithmException if the hashing algorithm is not available
     */
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) throws NoSuchAlgorithmException {
        String hashedInput = hashPassword(plainTextPassword);
        return hashedInput.equals(hashedPassword);
    }
}