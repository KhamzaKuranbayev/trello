package uz.genesis.trello.utils.pkcs;


import uz.genesis.trello.utils.BaseUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Crypto {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static final String SERVER_KEY = "SERVER_KEY_TRELLO_AES_256";


    public static void main(String[] args) {
        String macAddress = BaseUtils.defineMacAddress();

        if (!macAddress.isEmpty()) {
            /*String key = SERVER_KEY + Base64.getEncoder().encodeToString(macAddress.getBytes());

            System.out.println("Encrypted str : " + encrypt("{\n" +
                    "  \"company\":\"mib\",\n" +
                    "  \"registrade_date\":\"09.08.2019\",\n" +
                    "  \"maxCount\":\"50\"\n" +
                    "}", key));*/

            String key = SERVER_KEY + macAddress;
            System.out.println("Decrypted str : " + decrypt("lxv8OHvZRfylnPeFO4BkXboJVMoB6vtZgXw+wpA3U+xVxvtUtwiRBjUWtfmO7vEuL96oIB/ozCCB7645p6TTsREhPTlgMS3mwyJx7W0bQ80=", key));
        }
    }

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static String encodeBase(String paramOne) {
        StringBuilder builder = new StringBuilder();
        builder.append(BaseUtils.defineMacAddress());
        return Base64.getEncoder().encodeToString(builder.append(paramOne).toString().getBytes());
    }
}
