package raspi.wol;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "wol")
public class WakeOnLanConfig {
    private String macAddress;
    private Integer port;

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public Integer getPort() {
        return port;
    }
}
