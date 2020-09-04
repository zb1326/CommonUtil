package com.company.util.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CasDemo
 * @Description TODO
 * @Author zhubin
 * @Date 2020/9/1 14:30
 * @Version 1.0
 */
public class CasDemo {

    public static AtomicInteger atomicInteger = new AtomicInteger(5);

    public void addAtomic(){
        atomicInteger.getAndIncrement();
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {
        System.out.println(atomicInteger.compareAndSet(5,2000) + "," + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,3000) + "," + atomicInteger.get());
    }
}
