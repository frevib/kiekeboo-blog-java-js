package com.kiekeboo.app.services;

import com.kiekeboo.app.model.PasswordAndSaltModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordService {

    private final static Logger logger = LoggerFactory.getLogger(PasswordService.class);

    /**
     * Bcrypt is better! But for this simple blog hash+salt will suffice.
     * Take the hashed password from the database and the unhashed password from user. Check if hash(passwordFromPost + salt) == passwordFromDatabase
     *
     * @param passwordAndSaltFromDatabase
     * @param unhashedPasswordFromPost
     * @return true/false, depending on if password given by user == password from database.
     */
    public static boolean checkPassword(PasswordAndSaltModel passwordAndSaltFromDatabase, String unhashedPasswordFromPost) {
        logger.info("Checking password");
        String passwordAndSaltString = unhashedPasswordFromPost + passwordAndSaltFromDatabase.getSalt();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(passwordAndSaltString.getBytes());
        byte[] hashedBytes = md.digest();
        String hashedString = bytesToHex(hashedBytes);
        // TODO: only use lowercase in the database or else it will fail
        return equalsSafe(hashedString.toLowerCase(), passwordAndSaltFromDatabase.getPassword());
    }

    /**
     * Checks two different Strings if they are equal. To protect from timing attacks, XORing is used.
     *
     * @param one
     * @param two
     * @return true/false, depending on if one == two.
     */
    public static boolean equalsSafe(String one, String two) {
        boolean value = false;
        if (one.length() != two.length()) {
            return value;
        }
        char[] charOne = one.toCharArray();
        char[] charTwo = two.toCharArray();
        int length = one.length();
        int total = 0;
        for(int i = 1; i < length; i++) {
            total += charOne[i] ^ charTwo[i];
        }
        if(total == 0) {
            value = true;
        }
        return value;
    }

    // Converts a byte array to a hex String Taken from http://stackoverflow.com/a/9855338/3952065 but verified OK.
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
