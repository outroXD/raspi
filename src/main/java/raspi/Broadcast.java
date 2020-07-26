package raspi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

@Component
public class Broadcast {
    private static final Logger logger = LoggerFactory.getLogger(Broadcast.class);

    public Boolean sendPacket(String macAddress, String ipAddress, Integer port) {
        logger.info("[Called: sendPacket]");
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ipAddress, port);
            byte[] packetByte = getMagickPacket(macAddress);
            DatagramPacket datagramPacket = new DatagramPacket(packetByte, packetByte.length, inetSocketAddress);
            new DatagramSocket().send(datagramPacket);

        } catch (Exception e) {
            logger.error("[Error: sendPacket]" + e.toString());
            return false;
        }
        return true;
    }

    private byte[] getMagickPacket(String macAddress) {
        logger.info("[Called: getMagickPacket]");
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
        logger.info("[Called: getMacAddressByte]");
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