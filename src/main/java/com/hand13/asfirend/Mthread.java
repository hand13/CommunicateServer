package com.hand13.asfirend;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hd110 on 2017/7/21.
 */
public class Mthread {
    public static void main(String [] args){
        Aoto aoto=new Aoto();
        ExecutorService service= Executors.newFixedThreadPool(100);
        for(int i=0;i<100;i++){
            service.execute(()->System.out.println(aoto.getCount()));
        }
        service.shutdown();
    }
}
