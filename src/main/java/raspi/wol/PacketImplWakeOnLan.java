package raspi.wol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import raspi.log.LogUtil;
import raspi.packet.PacketAbstract;

public class PacketImplWakeOnLan extends PacketAbstract {
    private static final Logger logger = LoggerFactory.getLogger(PacketImplWakeOnLan.class);

    public PacketImplWakeOnLan(String ipAddress, String macAddress, Integer port) {
        super(ipAddress, macAddress, port);
    }

    protected byte[] getPacket(String macAddress) {
        logger.info(LogUtil.getLogOutMethodNameInfo(new Object(){}.getClass().getEnclosingClass().getName()));
        byte[] packet = new byte[102];
        int index = 0;
        for (int i = 0; i < 6; i++) {
            packet[index++] = (byte)0xff;
        }

        byte[] macAddressByte = getMacAddressByte(macAddress);
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
