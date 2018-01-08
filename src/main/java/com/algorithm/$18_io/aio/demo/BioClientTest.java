package com.algorithm.$18_io.aio.demo;

import java.io.*;
import java.net.Socket;

/**
 * xxx
 *
 * @author:v_fanhaibo on 2018/1/8.
 * @version:v1.0
 */

public class BioClientTest {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        bw.write("client: i am client, hello demo!!!");
        bw.flush();
        InputStream inputStream = socket.getInputStream();


        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String read ;
        while ( (read = br.readLine()) != null){
            System.out.println( read);

        }

        bw.close();
        outputStream.close();
        socket.close();

    }
}
