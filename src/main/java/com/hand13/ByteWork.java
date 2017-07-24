package com.hand13;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by hd110 on 2017/7/22.
 */
public class ByteWork {
    private  ArrayList<String> strings;
    public  void workInMessage(byte[] message)throws UnsupportedEncodingException{
        String mess=new String(message,0,message.length-2,"UTF-8");
        String [] messages=mess.split("\r");
        if(messages[0].equals("up")&&messages.length==4){
            String fname=messages[1];
            String tname=messages[2];
            String mesg=messages[3];
            try {
                Confirm.update(fname, tname, mesg);
            }
            catch (ClassNotFoundException ioe){
            }
            catch (SQLException ioe){
                System.out.println(ioe.getMessage());
            }
        }
        else if(messages[0].equals("down")&&messages.length==2){
            String tname=messages[1];
            try {
                strings=Confirm.down(tname);
            }
            catch (ClassNotFoundException ioe){
            }
            catch (SQLException ioe){
                System.out.println(ioe.getMessage());
            }
        }
    }
    public  ArrayList<String> wotkOutMessage(){
        try {
            return strings;
        }
        finally {
            strings=null;
        }
    }
}
