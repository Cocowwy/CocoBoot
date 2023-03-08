package cn.cocowwy.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/8
 */
public class AesBase64Utils {

    private static final String SECRET = "&^*cn.cocowwy*^&";
    private static final byte[] SECRET_BYTES = SECRET.getBytes(StandardCharsets.UTF_8);

    public static String encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_BYTES, "AES");
        IvParameterSpec iv = new IvParameterSpec(SECRET_BYTES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.encodeBase64(encryptedBytes));
    }

    public static String decrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_BYTES, "AES");
        IvParameterSpec iv = new IvParameterSpec(SECRET_BYTES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        byte[] bytesAfterBase64Decryption = Base64.decodeBase64(message);
        return new String(cipher.doFinal(bytesAfterBase64Decryption));
    }

}
