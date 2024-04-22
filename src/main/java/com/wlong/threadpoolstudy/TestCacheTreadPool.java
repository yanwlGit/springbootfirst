package com.wlong.threadpoolstudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 创建一个可以缓存的线程池，如果线程池长度超过处理需要，可以灵活回收空闲线程，没回收的话就新建线程。
 *
 * 总结: 线程池的最大核心线程为无限大，当执行第二个任务时第一个任务已经完成，
 *   则会复用执行第一个任务的线程；如果第一个线程任务还没有完成则会新建一个线程。
 * 特点：
 * 在接收新的异步任务target执行目标实例时，如果池内所有线程繁忙，此线程池就会添加新线程来处理任务
 * 线程池不会对线程池大小进行限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小
 * 如果部分线程空闲，也就是存量线程的数量超过了处理任务数量，就会回收空闲（60秒不执行任务）线程
 * 适用场景：
 * 需要快速处理突发性强、耗时较短的任务场景，如Netty的NIO处理场景、REST API接口的瞬时削峰场景
 * 缺点：
 * 线程池没有最大线程数量限制，如果大量的异步任务执行目标实例同时提交，可能会因创建线程过多而导致资源耗尽
 * */
public class TestCacheTreadPool {

    public static void main(String[] args)  {
        // 创建可缓存线程池
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            //创建任务 lambda
            Runnable runnable = () -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            };
            newCachedThreadPool.execute(runnable);
        }
    }

}
