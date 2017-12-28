package chatqq.home.com.app2.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by PVer on 2017/7/5.
 */

public class ThreadPoolTest {

    private static ExecutorService executorService1= Executors.newCachedThreadPool();
    private static ExecutorService executorService2= Executors.newFixedThreadPool(5);
    private static ExecutorService executorService3= Executors.newSingleThreadExecutor();

    private static MyThreadPool myThreadPool=new MyThreadPool(5,10);


    public static void main(String[] args){
        for (int i=0;i<10;i++){
//            myThreadPool.executor(new Runnable() {
//                @Override
//                public void run() {
//                    getTime();
//                }
//            });
        }
    }

    private static void getTime() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"="+System.currentTimeMillis());

    }

//    public ThreadPoolExecutor(int corePoolSize,
//                              int maximumPoolSize,
//                              long keepAliveTime,
//                              TimeUnit unit,
//                              BlockingQueue<Runnable> workQueue,
//                              ThreadFactory threadFactory) {
//        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
//                threadFactory, defaultHandler);

    static class MyThreadPool{
        private ThreadPoolExecutor executor;

        public MyThreadPool(int corePoolSize, int maximumPoolSize){

            LinkedBlockingQueue<Runnable> workQueue=new LinkedBlockingQueue<Runnable>(100);
            /**
             * 1.核心线程池大小
             * 2.最大线程池大小
             * 3.大于corePoolSize的线程执行完多少秒被杀死
             * 4.keepAliveTime的时间单位
             * 5.要执行的任务队列
             * 6.线程工厂用于创建线程
             */

            executor=new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 60L, TimeUnit.SECONDS,
                    workQueue, new ThreadFactory() {

                private AtomicInteger count=new AtomicInteger(0);


                @Override
                public Thread newThread(Runnable r) {

                    Thread thread=new Thread(r,"thread-"+count.incrementAndGet());

                    return thread;
                }
            });


        }

    }


}
