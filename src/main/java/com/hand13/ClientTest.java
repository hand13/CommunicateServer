package com.hand13;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by hd110 on 2017/7/19.
 */
public class ClientTest {
    public static void main(String [] args)throws IOException{
        Socket socket=new Socket();
        SocketAddress socketAddress=new InetSocketAddress("localhost",4321);
        socket.connect(socketAddress);
        if(!socket.isConnected()) {
            System.out.println("haha");
            return;
        }
        OutputStream out=socket.getOutputStream();
        byte[] data="up\rtest\rtest\rtest\n\r".getBytes("UTF-8");
        out.write(data);
        InputStream inputStream=socket.getInputStream();
        ByteArrayOutputStream oub=new ByteArrayOutputStream();
        int length;
        byte[] buffer=new byte[1024];
        while ((length=inputStream.read(buffer))>0){
            oub.write(buffer,0,length);
            if(new String(buffer,length-2,2,"UTF-8").equals("\n\r")){
                byte[] message=oub.toByteArray();
                oub.reset();
                String ss=new String(message,0,message.length-2,"UTF-8");
                String[] mes=ss.split("\r");
                for(String s:mes){
                    System.out.println(s);
                }
            }
        }
        socket.close();
        System.out.println();
    }
}
