package com.nimo.configclient;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auther zgp
 * @desc
 * @date 2022/3/11
 */
public class App {

    public static void main(String[] args) {
        // 最小核心线程数 1
        // 非核心线程数是 3， 最大存活时间 1 s
        // 队列为 1
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (1,
                        3,
                        1, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(1));

        System.out.println("先往线程池丢 4 个任务， 激活非核心线程");
        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.submit(()->{
                System.out.println(Thread.currentThread().getName() + " 执行第一批任务");
            });
        }

        System.out.println("休息 2 s===== 等待上面的任务执行完成， 并且等待非核心线程死亡。");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前完成的任务数：" + threadPoolExecutor.getCompletedTaskCount());

        System.out.println("再往线程池里面丢 4 个任务，重新激活非核心线程。");
        for (int i = 0; i < 4; i++) {
            threadPoolExecutor.submit(()->{
                System.out.println(Thread.currentThread().getName() + " 执行第二批任务");
            });
        }
        threadPoolExecutor.shutdown();

    }
}
