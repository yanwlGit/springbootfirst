package com.wlong.threadpoolstudy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建一个定长的线程池，可控制最大并发数，超出的线程进行队列等待。
 *
 * 总结：创建指定长度的线程池，任务超出当前线程池执行线程数量则会一直等待，直到运行。
 *
 * 特点：
 * 如果线程数没有达到“固定数量”，每次提交一个任务线程池内就创建一个新线程，直到线程达到线程池固定的数量
 * 线程池的大小一旦达到“固定数量”就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程
 * 在接收异步任务的执行目标实例时，如果池中的所有线程均在繁忙状态，新任务会进入阻塞队列中（无界的阻塞队列）
 * 适用场景：
 * 需要任务长期执行的场景
 * CPU密集型任务
 * 缺点：
 * 内部使用无界队列来存放排队任务，当大量任务超过线程池最大容量需要处理时，队列无限增大，使服务器资源迅速耗尽
 * */
public class TestFixedThreadPool {

    public static final int SLEEP_GAP=1000;
    static class TargetTask implements Runnable{
        static AtomicInteger taskNo=new AtomicInteger(1);
        private String taskName;
        private CountDownLatch latch;
        public TargetTask(CountDownLatch latch)
        {
            this.latch=latch;
            taskName="task-"+taskNo;
            taskNo.incrementAndGet();
        }
        public void run()
        {
            System.out.println(taskName+" is doing...");
            try {
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(taskName+" end...");
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        ExecutorService pool=Executors.newFixedThreadPool(3);//创建含有3个线程的线程池,即是最多3个任务并行
        final CountDownLatch latch = new CountDownLatch(3);//线程计数器

        for(int i=0;i<5;i++)
        {
            pool.execute(new TargetTask(latch));
            pool.submit(new TargetTask(latch));
        }

        try{
            //使调用该方法的主线程处于等待状态，当倒数到0时主线程才执行。
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("XXX喝牛奶多线程处理异常",e);
        }

        pool.shutdown();
    }


    public static void test1(String[] args)  {
        // 创建定长线程池
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            //创建任务
            Runnable runnable = new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            };
            // 将任务交给线程池管理
            newFixedThreadPool.execute(runnable);
        }
    }

}
