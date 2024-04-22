package com.wlong.threadpoolstudy;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Executors类（并发包）提供了4种创建线程池方法，这些方法最终都是通过配置ThreadPoolExecutor的不同参数，来达到不同的线程管理效果。
 *
 * newCacheTreadPool
 *
 * 创建一个可以缓存的线程池，如果线程池长度超过处理需要，可以灵活回收空闲线程，没回收的话就新建线程
 *
 * newFixedThread
 *
 * 创建一个定长的线程池，可控制最大并发数，超出的线程进行队列等待。
 *
 * newScheduleThreadPool
 *
 * 可以创建定长的、支持定时任务，周期任务执行。
 *
 * newSingleExecutor
 *
 * 创建一个单线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 *
 * 推荐多线程用法
 * 推荐通过 new ThreadPoolExecutor() 的写法创建线程池，这样写线程数量更灵活，开发中多数用这个类创建线程。
 *
 */
public class TestThreadPool {

    public static final int SLEEP_GAP=1000;
    static class TargetTask implements Runnable{
        static AtomicInteger taskNo=new AtomicInteger(1);
        String taskName;
        public TargetTask()
        {
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
        }
    }
    static class TargetTaskWithError extends TargetTask{
        public void run()
        {
            super.run();//执行父类的run方法
            //可以捕获异常
            //throw new RuntimeException("Error from "+taskName);
        }
    }



    public static void main(String[]args){
        int poolSize =Runtime.getRuntime().availableProcessors() * 2;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(512);
        /*
        * 当任务添加到线程池中被拒绝时，直接丢弃任务，并抛出 RejectedExecutionException异常
        * AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        *
        * 当任务添加到线程池中被拒绝时，丢弃被拒绝的任务，不抛异常。
        * DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        *
        * 当任务添加到线程池中被拒绝时，丢弃任务队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中
        * DiscardOldestPolicy discardOldestPolicy =new ThreadPoolExecutor.DiscardOldestPolicy();
        *
        * 被拒绝任务的处理程序，直接在execute方法的调用线程中运行被拒绝的任务。
        * 总结：就是被拒绝的任务，直接在主线程中运行，不再进入线程池。
        * CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        * */
        RejectedExecutionHandler policy = new ThreadPoolExecutor.AbortPolicy();
        /*
        * corePoolSize 线程池中的核心线程数核心线程生命周期无限，即使空闲也不会死亡。
        * maximumPoolSize 线程池中最大线程数 任务队列满了以后当有新任务进来则会增加一个线程来处理新任务，
        *   （线程总数 < maximumPoolSize）
        * keepAliveTime 闲置超时时间 当线程数大于核心线程数时，经过keepAliveTime时间将会回收非核心线程
        * unit 超时时间的单位（时/分/秒等）
        * workQueue 线程池中的任务队列 存放任务(Runnable)的容器
        * threadFactory 线程池提供创建新线程的线程工厂 不传默认使用 Executors.defaultThreadFactory()
        * rejectedExecutionHandler 拒绝策略 新增一个任务到线程池，如果线程池任务队列超过最大值之后,
        *   并且已经开启到最大线程数时，默认为抛出ERROR异常
        *
        * */
        ExecutorService executorService = new ThreadPoolExecutor(poolSize, poolSize,
                0, TimeUnit.SECONDS, queue, policy);

        executorService.execute(new TargetTaskWithError());
        Future future=executorService.submit(new TargetTaskWithError());
        try {
            if(future.get()==null) {
                System.out.println("No Exception");
            }
            Thread.sleep(1000);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
