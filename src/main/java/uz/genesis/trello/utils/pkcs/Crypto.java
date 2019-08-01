package uz.genesis.trello.utils.pkcs;


import net.minidev.json.JSONArray;
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
            String key = MD5.getMd5("DEF_SERVER_USER_ERVFG_NSTAC") + Base64.getEncoder().encodeToString(BaseUtils.defineMacAddress().getBytes());

//            System.out.println("Encrypted str : " + encrypt("{\n" +
//                    "  \"organization\": \"MCHJ YAKUBOV\",\n" +
//                    "  \"organizationId\": 1,\n" +
//                    "  \"certificate_date\": \"29.07.2019\",\n" +
//                    "  \"token_limit\": 4\n" +
//                    "}", key));

//           String key = MD5.getMd5("DEF_SERVER_USER_ERVFG_NSTAC") + Base64.getEncoder().encodeToString(BaseUtils.defineMacAddress().getBytes());
            System.out.println("Decrypted str : " + decodeBase("DEF_SERVER_USER_ERVFG_NSTAC", "ODtlewyuWz6bgQcBGIFWZdWjTFYdQfaMK0Vfjs4GhGEYVN2FRMZha6YfUa+bDWmv11ud1/eEC2iPFSulXNOZUjUUrWLDBJF5I2Fnl6dnGRITY3LPf6RGj5KNKvN/0cfYrLBopRN40X09HZHhimLJwFUyXIXitHp6Jvdn8v/wj8k="));
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

    private static String encrypt(String strToEncrypt, String secret) {
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

    private static String decrypt(String strToDecrypt, String secret) {
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

    public static String encodeBase(String paramOne, String paramSecond) {
        JSONArray array = new JSONArray();
        array.add(MD5.getMd5(paramOne));
        array.add(paramSecond);
        array.add(Base64.getEncoder().encodeToString(BaseUtils.defineMacAddress().getBytes()));
        return encrypt(array.toJSONString(), MD5.getMd5(paramOne) + Base64.getEncoder().encodeToString(BaseUtils.defineMacAddress().getBytes()));
    }

    public static String decodeBase(String paramOne, String encodedText) {
        return decrypt(encodedText, MD5.getMd5(paramOne) + /*"MUMtMUItMEQtNUEtQ0MtODA="*/Base64.getEncoder().encodeToString(BaseUtils.defineMacAddress().getBytes()));
    }
}
