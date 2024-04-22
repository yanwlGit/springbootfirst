package com.wlong.threadpoolstudy;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建定长的、支持定时任务，周期任务执行。
 *
 * 总结：以下案例中延迟2秒后开始执行线程池中的所有任务。主要问题在于线程数不设上限
 * */
public class TestScheduledThreadPool {
    public static void main(String[] args)  {
        // 创建支持定时线程池
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 5; i++) {
            //创建任务
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            };
            // 将任务交给线程池管理，延迟2秒后才开始执行线程池中的所有任务
            newScheduledThreadPool.schedule(runnable, 2, TimeUnit.SECONDS);
        }
    }

}
