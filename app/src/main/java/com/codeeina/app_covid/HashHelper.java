package com.codeeina.app_covid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashHelper {

    public static String get_SecurePassword(String passwordToHash) {
        String generatedPassword = null;
        byte[] salt = getSalt();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() {
        String s = "usethisforcodegeneration";
        byte[] sr = s.getBytes();
        byte[] salt = new byte[16];
        System.arraycopy(sr, 0, salt, 0, 16);
        return salt;
    }
}