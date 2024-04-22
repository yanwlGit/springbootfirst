package com.wlong.multithread;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyThreadTest {

    @Test
    public void test(){

        /**
         * 使用继承的方式测试
         * 如果使用了run方法就是普通苹的方法，不是线程
         * */
        /*Thread a1=new TestThreadA("a1");
        Thread a2=new TestThreadA("a2");
        a1.run();
        a2.run();*/

        /**
         * 使用继承的方式测试
         * 使用 start 方式，就是独立的线程
         * 两个子线程不添加如何判断机制，如果主线程结束，子线程会被强制结束
         * */
        /*Thread a3=new TestThreadA("a3");
        Thread a4=new TestThreadA("a4");
        a3.start();
        a4.start();*/

        /**
         * TestThreadA 使用反射的形式实现
         * 使用实现接口的方式测试且添加等待机制，等待子线程结束后主线程在结束
         *
         * */
        try{
            Class class5=Class.forName("com.wlong.multithread.TestThreadA");
            Constructor<?> con=class5.getConstructor(String.class);
            Method start=class5.getMethod("start");
            Method join=class5.getMethod("join");
            Object fsa3=con.newInstance("fsa3");
            Object fsa4=con.newInstance("fsa4");
            start.invoke(fsa3);
            start.invoke(fsa4);
            join.invoke(fsa3);
            join.invoke(fsa4);

        }catch (Exception e){
            System.out.println("error---->"+e.getMessage());
        }

        /**
         * 使用实现接口的方式测试且添加等待机制，等待子线程结束后主线程在结束
         *
         * */
        /*Thread a5=new Thread(new TestThreadB("a5"));
        Thread a6=new Thread(new TestThreadB("a6"));
        a5.start();
        a6.start();
        try{
            a5.join();
            a6.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }*/


    }

}

class TestThreadA extends  Thread{

    private String name;

    public TestThreadA(String name){
        this.name=name;
    }

    @Override
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println(name+"---->"+(i+1));
        }
    }
}

class TestThreadB implements Runnable{

    private String name;

    public TestThreadB(String name){
        this.name=name;
    }

    @Override
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println(name+"---->"+(i+1));
        }
    }
}