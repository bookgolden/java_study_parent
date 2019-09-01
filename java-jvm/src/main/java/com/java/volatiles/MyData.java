package com.java.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{
	
     int number = 0;
    public void addTo60(){
        this.number = 60;
    }
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtommic(){
        atomicInteger.getAndIncrement();
    }
}