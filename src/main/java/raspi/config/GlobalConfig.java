package raspi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {
    private String broadcastAddress;

    public void setBroadcastAddress(final String broadcastAddress) {
        this.broadcastAddress = broadcastAddress;
    }

    public String getBroadcastAddress() {
        return broadcastAddress;
    }
}
