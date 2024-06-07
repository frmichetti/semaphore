package main;

import java.util.concurrent.TimeUnit;

public class ThreePhasesSemaphore {

    private PhaseColor currentColor;
    private final ConfigSemaphore configSemaphore;

    public ThreePhasesSemaphore(ConfigSemaphore cfgSemap) {
        this.currentColor = PhaseColor.RED;
        this.configSemaphore = cfgSemap;
    }

    public void advance() {
        switch (currentColor) {
            case RED:
                advancePhase(PhaseColor.GREEN);
                break;
            case YELLOW:
                advancePhase(PhaseColor.RED);
                break;
            case GREEN:
                advancePhase(PhaseColor.YELLOW);
                break;
        }
    }

    private void advancePhase(PhaseColor currentPhaseColor){

        switch (currentPhaseColor){
            case PhaseColor.RED -> {
                awaitPhaseTime(configSemaphore.getTimeRed());
                currentColor = PhaseColor.RED;
                System.out.println("Mudando para fase: " + currentColor);
            }
            case PhaseColor.YELLOW -> {
                awaitPhaseTime(configSemaphore.getTimeYellow());
                currentColor = PhaseColor.YELLOW;
                System.out.println("Mudando para fase: " + currentColor);
            }
            case PhaseColor.GREEN -> {
                awaitPhaseTime(configSemaphore.getTimeGreen());
                currentColor = PhaseColor.GREEN;
                System.out.println("Mudando para fase: " + currentColor);
            }
        }

    }

    private void awaitPhaseTime(int timeInSec) {
        try {
            TimeUnit.SECONDS.sleep(timeInSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // MAIN
    public static void main(String[] args) {
        ConfigSemaphore configSemaphore = new ConfigSemaphore(5, 3, 10);
        ThreePhasesSemaphore semaphore = new ThreePhasesSemaphore(configSemaphore);

        while (true) {
            semaphore.advance();
        }
    }
}

enum PhaseColor {
    RED, YELLOW, GREEN
}

class ConfigSemaphore {
    private final int TimeRed;
    private final int timeYellow;
    private final int timeGreen;

    public ConfigSemaphore(int TimeRed, int timeYellow, int timeGreen) {
        this.TimeRed = TimeRed;
        this.timeYellow = timeYellow;
        this.timeGreen = timeGreen;
    }

    public int getTimeRed() {
        return TimeRed;
    }

    public int getTimeYellow() {
        return timeYellow;
    }

    public int getTimeGreen() {
        return timeGreen;
    }

}