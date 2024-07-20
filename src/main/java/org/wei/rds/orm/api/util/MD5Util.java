package org.wei.rds.orm.api.util;

import java.security.MessageDigest;

/**
 * @description:
 * @author: deanwanghewei@gmail.com
 * @date: 2024年07月18日 16:03
 */
public class MD5Util {
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            return bytesToHex(digest);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
