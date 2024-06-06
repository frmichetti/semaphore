package useless;

import java.util.concurrent.Semaphore;

public class SemaphoreScript {

    public static void main(String[] args) {
        // Define the number of permits available (initial capacity)
        int permits = 2;
        Semaphore semaphore = new Semaphore(permits);

        // Create threads that will try to acquire permits
        for (int i = 0; i < 5; i++) {
            new Thread(new SemaphoreWorker(semaphore)).start();
        }
    }
}

class SemaphoreWorker implements Runnable {

    private final Semaphore semaphore;

    public SemaphoreWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // Acquire a permit, blocking the thread if none are available
            semaphore.acquire();
            System.out.println("Thread " + Thread.currentThread().getName() + " acquired permit. Accessing shared resource.");

            // Simulate work on the shared resource
            Thread.sleep(1000);

            // Release the permit, allowing another thread to acquire it
            semaphore.release();
            System.out.println("Thread " + Thread.currentThread().getName() + " released permit.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
