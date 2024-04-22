package com.wlong.threadpoolstudy;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个单线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 * 特点：
 * 单线程化的线程池中的任务是按照提交的次序顺序执行的
 * 只有一个线程的线程池
 * 池中的唯一线程的存活时间是无限的
 * 当池中的唯一线程正繁忙时，新提交的任务实例会进入内部的阻塞队列中，并且其阻塞队列是无界的
 * 适用场景：
 * 任务按照提交次序，一个任务一个任务地逐个执行的场景
 * 缺点：
 * 内部使用无界队列来存放排队任务，当大量任务需要处理时，队列无限增大，使服务器资源迅速耗尽
 * */
public class TestSingleExecutor {
    public static void main(String[] args)  {
        // 创建单线程-线程池，任务依次执行
        ExecutorService newScheduledThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            //创建任务
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            };
            // 将任务交给线程池管理
            newScheduledThreadPool.execute(runnable);
        }
    }

}
