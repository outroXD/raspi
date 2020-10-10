package raspi.wol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import raspi.log.LogUtil;
import raspi.packet.PacketAbstract;

@Service
public class PacketImplWakeOnLan extends PacketAbstract {
    private static final Logger logger = LoggerFactory.getLogger(PacketImplWakeOnLan.class);

    protected byte[] getPacket(String macAddress) {
        logger.info(LogUtil.getLogOutMethodNameInfo(new Object(){}.getClass().getEnclosingClass().getName()));

        byte[] packet = new byte[102];
        int index = 0;
        for (int i = 0; i < 6; i++) {
            packet[index++] = (byte)0xff;
        }

        byte[] macAddressByte = super.wakeOnLanConfig.getMacAddressBytes();
        for (int i = 0; i < 16; i++) {
            packet[index++] = macAddressByte[0];
            packet[index++] = macAddressByte[1];
            packet[index++] = macAddressByte[2];
            packet[index++] = macAddressByte[3];
            packet[index++] = macAddressByte[4];
            packet[index++] = macAddressByte[5];
        }
        return packet;
    }
}
