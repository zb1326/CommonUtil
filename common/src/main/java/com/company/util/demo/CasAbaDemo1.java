package com.company.util.demo;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName CasDemo
 * @Description TODO
 * @Author zhubin
 * @Date 2020/9/1 14:30
 * @Version 1.0
 */
public class CasAbaDemo1 {

    /**
     * 避免了aba问题
     * @param args
     */
    public static void main(String[] args) {
        User z3 = new User("z3",20);
        User l4 = new User("l4",25);
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(z3,0);
        new Thread(() -> {
            System.out.println("t1第一次获取值:" + "\t" + atomicStampedReference.getReference());
            System.out.println(atomicStampedReference.compareAndSet(z3,l4,0,1) + "\t t1第二次获取值:" + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(l4,z3,1,2) + "\t t1第三次获取值:" + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
        }, "thread-1").start();

        new Thread(() -> {
            try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("t2第一次获取值:" + "\t" + atomicStampedReference.getReference());

            System.out.println(atomicStampedReference.compareAndSet(z3,l4,0,1) + "\t t2第二次获取值:" + "\t" + atomicStampedReference.getReference() + "\t" + atomicStampedReference.getStamp());
        }, "thread-2").start();
    }
}


