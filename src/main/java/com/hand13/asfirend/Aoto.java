package com.hand13.asfirend;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hd110 on 2017/7/21.
 */
public class Aoto {
    private AtomicLong count=new AtomicLong(0);
    public long getCount(){
        return count.getAndIncrement();
    }
}
