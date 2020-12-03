package raspi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import java.io.UnsupportedEncodingException;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "crypt")
public class CryptConfig {

    @NonNull
    private String key;
    @NonNull
    private String vec;

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public byte[] getByteKey(final String format) throws UnsupportedEncodingException {
        return this.key.getBytes(format);
    }

    public String getVec() {
        return this.vec;
    }

    public void setVec(final String vec) {
        this.vec = vec;
    }

    public byte[] getByteVec(final String format) throws UnsupportedEncodingException {
        return this.vec.getBytes(format);
    }
}