package cn.cocowwy.cocobootmbstarter.interceptor.privacy;


import cn.cocowwy.cocobootmbstarter.interceptor.utils.AesBase64Utils;

/**
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/8
 */
public class DefaultEncryptionDecryption implements EncryptionDecryption {
    @Override
    public String encryption(String original) throws Exception {
        return AesBase64Utils.encrypt(original);
    }

    @Override
    public String decryption(String ciphertext) throws Exception {
        return AesBase64Utils.decrypt(ciphertext);
    }
}
