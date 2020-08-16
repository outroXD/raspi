package raspi.packet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import raspi.log.LogUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

abstract public class PacketAbstract {
    private static final Logger logger = LoggerFactory.getLogger(PacketAbstract.class);

    /* 送信先IPアドレス */
    private final String ipAddress;
    /* 送信先MACアドレス */
    private final String macAddress;
    /* 送信先ポート */
    private final Integer port;

    public PacketAbstract(String ipAddress, String macAddress, Integer port) {
        if (StringUtils.isEmpty(ipAddress)
                || StringUtils.isEmpty(macAddress)
                || port == null) {
            throw new IllegalArgumentException("Argument not allow null.");
        }
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.port = port;
    }

    public Boolean send() {
        logger.info(LogUtil.getLogOutMethodNameInfo(new Object(){}.getClass().getEnclosingClass().getName()));
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ipAddress, port);
            byte[] packetByte = this.getPacket(macAddress);
            DatagramPacket datagramPacket = new DatagramPacket(packetByte, packetByte.length, inetSocketAddress);
            new DatagramSocket().send(datagramPacket);

        } catch (Exception e) {
            logger.error("[Error: send]" + e.toString());
            return false;
        }
        return true;
    }

    private byte[] getPacket(String macAddress) {
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

    private byte[] getMacAddressByte(String macAddress) {
        logger.info(LogUtil.getLogOutMethodNameInfo(new Object(){}.getClass().getEnclosingClass().getName()));
        String[] macArray = macAddress.split("-");
        if (macArray.length != 6) {
            logger.error("[Error: getMacAddressByte] Mac Address Setting Error.");
        }

        byte[] macAddressByte = new byte[6];
        for (int i = 0; i < macArray.length; i++) {
            macAddressByte[i] = (byte)Integer.parseInt(macArray[i], 16);
        }
        return macAddressByte;
    }
}
