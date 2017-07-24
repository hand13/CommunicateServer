package com.hand13;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by hd110 on 2017/7/19.
 */
public class Server {
    public static void main(String [] args)throws IOException{
        ServerSocket serverSocket=new ServerSocket(4321);
        Socket clientSocket;
        ExecutorService executor= Executors.newFixedThreadPool(100);
        try {
            while (true) {
                clientSocket = serverSocket.accept();
                executor.submit(new ServerProcess(clientSocket));
            }
        }
        finally {
            executor.shutdown();
        }
    }
}
