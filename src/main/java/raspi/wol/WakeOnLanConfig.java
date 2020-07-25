package raspi.wol;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "wol")
public class WakeOnLanConfig {
    private String ip;
    private String mac;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public String getMac() {
        return mac;
    }

    public String toString() {
        return "{" + this.getMac() + ", " + this.getIp() + "}";
    }
}
