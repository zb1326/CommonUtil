package com.company.util.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName CasDemo
 * @Description TODO
 * @Author zhubin
 * @Date 2020/9/1 14:30
 * @Version 1.0
 */
public class CasAbaDemo {

    public static AtomicReference atomicReference = new AtomicReference();


    public static void main(String[] args) {
        User z3 = new User("z3",20);
        User l4 = new User("l4",25);
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3,new User("l4",30)) + "/t" + atomicReference.get());
        System.out.println(atomicReference.compareAndSet(z3,l4) + "/t" + atomicReference.get());


    }
}


class User{

    private String name;

    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
