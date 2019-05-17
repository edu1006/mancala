package br.com.petrim.lich.util;

import br.com.petrim.lich.exception.SecurityException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static byte[] getHash(String value, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(value.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("Error to generate hash", e);
        }
    }

    public static String hashHexa(String value, String algorithm) {
        byte[] hash = getHash(value, algorithm);

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            int parteAlta = ((hash[i] >> 4) & 0xf) << 4;
            int parteBaixa = hash[i] & 0xf;
            if (parteAlta == 0)
                s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

}
