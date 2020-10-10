package raspi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import raspi.log.LogUtil;
import java.util.regex.Pattern;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "wol")
public class WakeOnLanConfig {
    private static final Logger logger = LoggerFactory.getLogger(WakeOnLanConfig.class);

    @NonNull
    private String macAddress;
    private byte[] macAddressBytes;
    @NonNull
    private Integer port;

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public byte[] getMacAddressBytes() {
        logger.info(LogUtil.getLogOutMethodNameInfo(new Object(){}.getClass().getEnclosingClass().getName()));

        if (macAddressBytes != null) {
            return macAddressBytes;
        }

        String[] macArray = macAddress.split(Pattern.quote("-"));
        if (macArray.length != 6) {
            logger.error("[Error: getMacAddressByte] Mac Address Setting Error.");
        }
        macAddressBytes = new byte[6];
        for (int i = 0; i < macArray.length; i++) {
            macAddressBytes[i] = (byte)Integer.parseInt(macArray[i], 16);
        }
        return macAddressBytes;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }
}
