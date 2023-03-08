package cn.cocowwy.cocobootmbstarter.interceptor.privacy;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/8
 */
public interface EncryptionDecryption {

    /**
     * 加密
     * @param original 原文
     * @return 密文
     */
    String encryption(String original) throws Exception;

    /**
     * 解密
     * @param ciphertext 原文
     * @return 密文
     */
    String decryption(String ciphertext) throws Exception;

}
