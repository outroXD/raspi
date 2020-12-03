package raspi.packet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import raspi.config.GlobalConfig;
import raspi.config.WakeOnLanConfig;
import raspi.log.LogUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

abstract public class PacketAbstract {
    private static final Logger logger = LoggerFactory.getLogger(PacketAbstract.class);

    @Autowired
    protected GlobalConfig globalConfig;
    @Autowired
    protected WakeOnLanConfig wakeOnLanConfig;

    public Boolean send() {
        logger.info(LogUtil.getLogOutMethodNameInfo(new Object(){}.getClass().getEnclosingClass().getName()));

        try {
            final InetSocketAddress inetSocketAddress = new InetSocketAddress(globalConfig.getBroadcastAddress(), wakeOnLanConfig.getPort());
            byte[] packetByte = this.getPacket(wakeOnLanConfig.getMacAddress());
            final DatagramPacket datagramPacket = new DatagramPacket(packetByte, packetByte.length, inetSocketAddress);
            new DatagramSocket().send(datagramPacket);
        } catch (Exception e) {
            logger.error("[Error: send]" + e.toString());
            return false;
        }
        return true;
    }

    protected abstract byte[] getPacket(String macAddress);
}
