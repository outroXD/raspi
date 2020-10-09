package raspi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raspi.config.ApiConfig;
import raspi.config.GlobalConfig;
import raspi.config.WakeOnLanConfig;
import raspi.wol.PacketImplWakeOnLan;

@RestController
class BroadcastPacketRestController extends AbstractRestController {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastPacketRestController.class);
    private final GlobalConfig globalConfig;
    private final WakeOnLanConfig wakeOnLanConfig;

    @Autowired
    public BroadcastPacketRestController(ApiConfig apiConfig,
                                         GlobalConfig globalConfig,
                                         WakeOnLanConfig wakeOnLanConfig) {
        super(apiConfig);
        this.globalConfig = globalConfig;
        this.wakeOnLanConfig = wakeOnLanConfig;
    }

    @RequestMapping({"/broadcast/wol/start", "/broadcast/wol/start/{apikey}"})
    String wol(@PathVariable(name = "apikey") String apikey) {
        if (!isValidApiKey(apikey)) {
            logger.error("apiKey error.");
            return "Failed.";
        }

        PacketImplWakeOnLan packetImplWakeOnLan = new PacketImplWakeOnLan(
                globalConfig.getBroadcastAddress(),
                wakeOnLanConfig.getMacAddress(),
                wakeOnLanConfig.getPort());
        Boolean flag = packetImplWakeOnLan.send();

        if (flag)
            return "Success.";
        else
            return "Failed.";
    }
}