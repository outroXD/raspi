package raspi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raspi.wol.PacketImplWakeOnLan;

@RestController
class BroadcastPacketRestController extends AbstractRestController {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastPacketRestController.class);

    @Autowired
    PacketImplWakeOnLan packetImplWakeOnLan;

    @RequestMapping({"/broadcast/wol/start", "/broadcast/wol/start/{apiKey}"})
    public String wol(@PathVariable("apiKey") String apiKey) {
        if (!isValidApiKey(apiKey)) {
            logger.error("apiKey error.");
            return "Failed.";
        }

        boolean flag = packetImplWakeOnLan.send();

        if (!flag)
            return "Failed.";
        return "Success.";
    }
}