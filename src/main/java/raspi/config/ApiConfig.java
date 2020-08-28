package raspi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "api")
public class ApiConfig {
    private String apikey;

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getApikey() {
        return this.apikey;
    }
}