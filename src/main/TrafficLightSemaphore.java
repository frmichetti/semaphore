package main;

import java.util.Timer;
import java.util.TimerTask;

public class TrafficLightSemaphore {

    private static final int GREEN_DURATION = 5;
    private static final int YELLOW_DURATION = 2;
    private static final int RED_DURATION = 3;

    private volatile int currentPhase = 0; // 0: Red, 1: Green, 2: Yellow
    private volatile boolean isRunning = true;

    public void start() {
        new Thread(() -> {
            while (isRunning) {
                switch (currentPhase) {
                    case 0: // Red phase
                        System.out.println("VERMELHO");
                        waitForPhase(RED_DURATION);
                        break;
                    case 1: // Green phase
                        System.out.println("VERDE");
                        waitForPhase(GREEN_DURATION);
                        break;
                    case 2: // Yellow phase
                        System.out.println("AMARELO");
                        waitForPhase(YELLOW_DURATION);
                        break;
                }

                currentPhase = (currentPhase + 1) % 3; // Switch to the next phase

            }
        }).start();
    }

    public void waitForPhase(int duration) {
        try {
            Thread.sleep(duration * 1000); // Wait for green duration
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isRunning = false;
    }
}

class TrafficLightSemaphoreMain {

    static TrafficLightSemaphore tls =   new TrafficLightSemaphore();

    public static void main(String[] args) {
        final long execTime = 60 * 1000;

        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                tls.start();
                System.out.println("Executando código por 60s...");
            }
        };

        timer.scheduleAtFixedRate(tt, 0, execTime);

        // Aguarda o término da execução do código
        try {
            Thread.sleep(execTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cancela o timer
        tls.stop();
        timer.cancel();
        System.out.println("Código finalizado.");

    }
}
