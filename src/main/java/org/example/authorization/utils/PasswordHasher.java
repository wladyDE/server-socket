package org.example.authorization.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHasher {

    public static String hashPassword(String password, String login) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), login.getBytes(), 65536, 128);
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyPassword(String enteredPassword, String hashedPasswordFromDatabase){
        return enteredPassword.equals(hashedPasswordFromDatabase);
    }
}