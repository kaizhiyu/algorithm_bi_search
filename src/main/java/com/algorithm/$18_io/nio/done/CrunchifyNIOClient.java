package com.algorithm.$18_io.nio.done;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author Crunchify.com
 */

public class CrunchifyNIOClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        for (int i = 0; i < 99; i++) {
            sssss(i);
        }
    }

    private static void sssss(int num) throws IOException, InterruptedException {
        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);
//        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);
        SocketChannel crunchifyClient = SocketChannel.open(crunchifyAddr);

//        log("Connecting to Server on port 1111...");

        ArrayList<String> companyDetails = new ArrayList<String>();

        // create a ArrayList with companyName list
        companyDetails.add(" == "+num + " == Facebook=");
//        companyDetails.add("Twitter");
//        companyDetails.add("IBM");
//        companyDetails.add("Google");
        companyDetails.add("Crunchify" + num);

        for (String companyName : companyDetails) {

            ByteBuffer buffer = ByteBuffer.wrap(companyName.getBytes());
            crunchifyClient.write(buffer);

//            log("sending: " + companyName);
            buffer.clear();

            // wait for 2 seconds before sending next message
//            Thread.sleep(2000);
        }
        crunchifyClient.close();
    }

//    private static void log(String str) {
//        System.out.println(str);
//    }
}