package main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SImpExecutor {
    public static void main(String[] args) {


        System.out.println("Iniciando");

        CountDownLatch cdl1 = new CountDownLatch(5);
        CountDownLatch cdl2 = new CountDownLatch(5);
        CountDownLatch cdl3 = new CountDownLatch(5);
        CountDownLatch cdl4 = new CountDownLatch(5);

        ExecutorService es = Executors.newFixedThreadPool(1);

        es.execute(new MyThread(cdl1, "Thread A"));
        es.execute(new MyThread(cdl2, "Thread B"));
        es.execute(new MyThread(cdl3, "Thread C"));
        es.execute(new MyThread(cdl4, "Thread D"));

        try{
            cdl1.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException e) {
           System.out.println(e);
        }


        es.shutdown();
        System.out.println("Terminado");



    }
}
