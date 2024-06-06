public class TrafficLightSemaphore {

    private static final int GREEN_DURATION = 5; // Duration of green phase in seconds
    private static final int YELLOW_DURATION = 2; // Duration of yellow phase in seconds
    private static final int RED_DURATION = 3; // Duration of red phase in seconds

    private volatile int currentPhase = 0; // 0: Red, 1: Yellow, 2: Green
    private volatile boolean isRunning = true; // Flag to control loop execution

    public void start() {
        new Thread(() -> {
            while (isRunning) {
                switch (currentPhase) {
                    case 0: // Red phase
                        System.out.println("Red light is on.");
                        try {
                            Thread.sleep(RED_DURATION * 1000); // Wait for red duration
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1: // Yellow phase
                        System.out.println("Yellow light is on.");
                        try {
                            Thread.sleep(YELLOW_DURATION * 1000); // Wait for yellow duration
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2: // Green phase
                        System.out.println("Green light is on.");
                        try {
                            Thread.sleep(GREEN_DURATION * 1000); // Wait for green duration
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }

                currentPhase = (currentPhase + 1) % 3; // Switch to the next phase
            }
        }).start();
    }

    public void stop() {
        isRunning = false;
    }
}
