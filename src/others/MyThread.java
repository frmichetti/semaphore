package others;

import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable {

    CountDownLatch cdl;
    String name;

    MyThread(CountDownLatch c, String n) {
        this.cdl = c;
        this.name = n;
        new Thread(this);
    }

    @Override
    public void run() {
       for (int i = 0; i < 5; i++) {
           System.out.println(this.name + " :" + i);
           this.cdl.countDown();
       }
    }
}
