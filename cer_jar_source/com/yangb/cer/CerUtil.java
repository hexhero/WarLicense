package com.yangb.cer;

import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * cer
 *
 * @author create by yangb in 2019/6/5
 */
public class CerUtil {

    public static void main(String[] args) throws IOException {
        String s = decoder(new ByteArrayInputStream("MTkyLjE2OC4xMC4xODk6NkMtMkItNTktMzMtN0EtRUQ6MjAxOS0wNS0wNjoyMDIwLTAxLTAx".getBytes()));
        System.out.println(s);
    }
    public static String getMacByIp(String ip) throws UnknownHostException, SocketException {
        String[] sarr = ip.split("\\.");
        byte[] bytes = new byte[sarr.length];
        for (int i = 0; i < sarr.length; i++) {
            bytes[i] = (byte) Integer.parseInt(sarr[i]);
        }
        InetAddress address = InetAddress.getByAddress(bytes);
        String mac = getMac(address);
        return mac;
    }

    public static String getMac(InetAddress ia) throws SocketException {
        NetworkInterface anInterface = NetworkInterface.getByInetAddress(ia);
        StringBuffer buffer = new StringBuffer();
        byte[] address = anInterface.getHardwareAddress();
        for (int i = 0; i < address.length; i++) {
            int a = address[i] & 0xff;
            String hex = Integer.toHexString(a);
            if(a<10){
                hex = "0"+hex;
            }
            if(i < address.length - 1 ){
                hex += "-";
            }
            buffer.append(hex.toUpperCase());
        }
        return buffer.toString();
    }

    public static String decoder(InputStream in) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(in);
        String cert = new String(bytes);
        return cert;
    }

}
