package com.hand13;
import javax.print.attribute.standard.MediaSize;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by hd110 on 2017/7/19.
 */
public class ServerProcess implements Runnable {
    private Socket socket;
    public ServerProcess(Socket socket){
        this.socket=socket;
    }
    public void run(){
        try {
            InputStream in = socket.getInputStream();
            OutputStream out=socket.getOutputStream();
            MessageWork messageWork=new MessageWork(in,out);
            messageWork.work();
            socket.close();
        }
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}
