package raspi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raspi.config.GlobalConfig;
import raspi.config.WakeOnLanConfig;
import raspi.wol.PacketImplWakeOnLan;

@RestController
class BroadcastPacketRestController {
    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private WakeOnLanConfig wakeOnLanConfig;

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