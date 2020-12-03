package raspi.crypt;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raspi.config.CryptConfig;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class Aes implements ICrypt {
    private static final Logger logger = LoggerFactory.getLogger(Aes.class);

    @Autowired
    private CryptConfig cryptConfig;
    private static final String FORMAT_UTF_8 = "UTF-8";

    public Optional<String> encrypt(final String text) {
        String res = null;
        try {
            byte[] byteText = text.getBytes(StandardCharsets.UTF_8);
            final SecretKeySpec keySpec = new SecretKeySpec(cryptConfig.getByteKey(FORMAT_UTF_8), "AES");
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(cryptConfig.getByteVec(FORMAT_UTF_8));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

            byte[] byteResult = cipher.doFinal(byteText);
            res = Base64.encodeBase64String(byteResult);

        } catch (UnsupportedEncodingException
                | NoSuchPaddingException
                | NoSuchAlgorithmException
                | InvalidAlgorithmParameterException
                | InvalidKeyException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            logger.error("[Error: encrypt]" + e.toString());
        }
        return Optional.ofNullable(res);
    }

    public Optional<String> decrypt(final String text) {
        String res = null;
        try {
            byte[] byteText = Base64.decodeBase64(text);
            final SecretKeySpec keySpec = new SecretKeySpec(cryptConfig.getByteKey(FORMAT_UTF_8), "AES");
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(cryptConfig.getByteVec(FORMAT_UTF_8));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

            byte[] byteResult = cipher.doFinal(byteText);
            res = new String(byteResult, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | NoSuchPaddingException
                | UnsupportedEncodingException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            logger.error("[Error: decrypt]" + e.toString());
        }
        return Optional.ofNullable(res);
    }
}
