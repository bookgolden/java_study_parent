package com.java.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class User{
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
public class AtomicReferenceDemo {
    public static void main(String[] args){
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User zs = new User("zs",22);
        User ls = new User("ls",25);

        atomicReference.set(zs);
//        System.out.println(atomicReference.compareAndSet(zs,ls)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zs, ls)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zs, ls)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(ls, zs)+"\t"+atomicReference.get().toString());
    }
}
