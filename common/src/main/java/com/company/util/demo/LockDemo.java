package com.company.util.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockDemo
 * @Description TODO
 * @Author zhubin
 * @Date 2020/9/4 10:26
 * @Version 1.0
 */
public class LockDemo {

    public static Lock lock = new ReentrantLock();
//    public static Lock lock = new ReentrantLock(true);

    public void getFair(){

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "获得了锁！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void getUnFair(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "获得了锁！");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDemo lockDemo = new LockDemo();

//        for (int i = 1; i <= 10; i++){
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + "\t" +"启动");
//                lockDemo.getFair();
//                    },String.valueOf(i)).start();
//        }

        System.out.println("================================================");

        for (int i = 1; i <= 10; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" +"启动");
                lockDemo.getUnFair();
                    },String.valueOf(i)).start();
        }


    }
}
