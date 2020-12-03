package raspi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "api")
public class ApiConfig {
    @NonNull
    private String apiKey;

    public void setApikey(final String apikey) {
        this.apiKey = apikey;
    }

    public String getApikey() {
        return this.apiKey;
    }
}