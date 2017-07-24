package com.hand13;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by hd110 on 2017/7/22.
 */
public class MessageWork {
    private InputStream in;
    private OutputStream out;
    private byte[] buffer;
    public MessageWork(InputStream in,OutputStream out){
        this.in=in;
        this.out=out;
        buffer=new byte[1024];
    }
    public void work(){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        int length;
        try {
            while ((length = in.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
                if(new String(buffer,length-2,2,"UTF-8").equals("\n\r")){
                    byte[] message=outputStream.toByteArray();
                    outputStream.reset();
                    ByteWork byteWork=new ByteWork();
                    byteWork.workInMessage(message);
                    ArrayList<String> strings=byteWork.wotkOutMessage();
                    sendMessage(strings);
                }
            }
        }
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
    public void sendMessage(ArrayList<String> strings){
        if(strings==null)
            return;
        StringBuilder stringBuilder=new StringBuilder();
        for(String s:strings){
            stringBuilder.append(s+"\r");
        }
        stringBuilder.append("\n\r");
        String message=stringBuilder.toString();
        try {
            out.write(message.getBytes("UTF-8"));
        }
        catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }
}
