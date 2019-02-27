package com.studentIS.hash;

import java.util.Random;

public class Helper {

    private static final String charset = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int length = charset.length();
    private static final Random r = new Random();

    public static void main(String[] args) throws Exception {
        String password = "test";
        String salt = createSalt();
        System.out.println("Salt: " + salt);

        String hash = Hash.hash(password, salt);

        System.out.println("Hash: " + hash);
    }

    public static String createSalt() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 8; i++) {
            sb.append(charset.charAt(r.nextInt(length)));
        }
        return sb.toString();
    }
}
