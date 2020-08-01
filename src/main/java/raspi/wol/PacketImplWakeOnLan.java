package raspi.wol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import raspi.packet.PacketAbstract;

public class PacketImplWakeOnLan extends PacketAbstract {
    private static final Logger logger = LoggerFactory.getLogger(PacketImplWakeOnLan.class);

    public PacketImplWakeOnLan(String ipAddress) {
        super(ipAddress);
    }
}
