package useless;

import java.util.Timer;
import java.util.TimerTask;

public class TimerMain {

    public static void main(String[] args) {
        final long tempoExecucao = 60000; // 1 minuto em milissegundos

        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                // Seu código Java aqui
                System.out.println("Executando código...");
            }
        };

        timer.scheduleAtFixedRate(tarefa, 0, tempoExecucao);

        // Aguarda o término da execução do código
        try {
            Thread.sleep(tempoExecucao);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cancela o timer
        timer.cancel();
        System.out.println("Código finalizado.");
    }
}
