package raspi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raspi.config.GlobalConfig;
import raspi.config.WakeOnLanConfig;
import raspi.wol.PacketImplWakeOnLan;

@RestController
class BroadcastPacketRestController {
    private final GlobalConfig globalConfig;
    private final WakeOnLanConfig wakeOnLanConfig;

    @Autowired
    public BroadcastPacketRestController(GlobalConfig globalConfig, WakeOnLanConfig wakeOnLanConfig) {
        this.globalConfig = globalConfig;
        this.wakeOnLanConfig = wakeOnLanConfig;
    }

    @RequestMapping("/broadcast/wol/start")
    String wol() {
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