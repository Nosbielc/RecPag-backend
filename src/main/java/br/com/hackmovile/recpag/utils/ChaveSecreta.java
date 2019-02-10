package br.com.hackmovile.recpag.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.StringJoiner;

public abstract class ChaveSecreta {

    public static String getHashTransacao(String key1, String key2, String key3, String key4) {
        String s =
                new StringJoiner(":","","")
                        .add(key1)
                        .add(key4)
                        .add(key2)
                        .add(key4)
                        .add(key3)
                        .toString();
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update( s.getBytes(), 0 , s.length() );
            byte[] digest = m.digest();
            return new BigInteger(1,digest).toString(120);
        } catch (Exception e) {
            return String.valueOf(System.currentTimeMillis() + ":" +System.currentTimeMillis());
        }
    }

}
