import com.sun.jdi.PathSearchingVirtualMachine;

public class TrafficLightSemaphore {

    private static final int GREEN_DURATION = 5;
    private static final int YELLOW_DURATION = 2;
    private static final int RED_DURATION = 3;

    private volatile int currentPhase = 0; // 0: Red, 1: Green, 2: Yellow
    private volatile boolean isRunning = true; // Flag to control loop execution

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
    public static void main(String[] args) {
        new TrafficLightSemaphore().start();
    }
}
