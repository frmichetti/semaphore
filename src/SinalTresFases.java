import java.util.concurrent.TimeUnit;

public class SinalTresFases {

    private CorSinal corAtual;
    private final ConfiguracaoSinal configuracaoSinal;

    public SinalTresFases(ConfiguracaoSinal configuracaoSinal) {
        this.corAtual = CorSinal.VERMELHO;
        this.configuracaoSinal = configuracaoSinal;
    }

    public void avancaFase() {
        switch (corAtual) {
            case VERMELHO:
                avançaSinal(CorSinal.VERDE);
                break;
            case AMARELO:
                avançaSinal(CorSinal.VERMELHO);
                break;
            case VERDE:
                avançaSinal(CorSinal.AMARELO);
                break;
        }
    }

    private void avançaSinal(CorSinal corSinalAtual){

        switch (corSinalAtual){
            case CorSinal.VERMELHO -> {
                esperaTempoFase(configuracaoSinal.getTempoVermelho());
                corAtual = CorSinal.VERMELHO;
                System.out.println("Mudando para fase: " + corAtual);
            }
            case CorSinal.AMARELO -> {
                esperaTempoFase(configuracaoSinal.getTempoAmarelo());
                corAtual = CorSinal.AMARELO;
                System.out.println("Mudando para fase: " + corAtual);
            }
            case CorSinal.VERDE -> {
                esperaTempoFase(configuracaoSinal.getTempoVerde());
                corAtual = CorSinal.VERDE;
                System.out.println("Mudando para fase: " + corAtual);
            }
        }

    }

    private void esperaTempoFase(int tempoSegundos) {
        try {
            TimeUnit.SECONDS.sleep(tempoSegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // MAIN
    public static void main(String[] args) {
        ConfiguracaoSinal configuracaoSinal = new ConfiguracaoSinal(5, 3, 10);
        SinalTresFases semaforo = new SinalTresFases(configuracaoSinal);

        while (true) {
            semaforo.avancaFase();
        }
    }
}

enum CorSinal {
    VERMELHO, AMARELO, VERDE
}

class ConfiguracaoSinal {
    private final int tempoVermelho;
    private final int tempoAmarelo;
    private final int tempoVerde;

    public ConfiguracaoSinal(int tempoVermelho, int tempoAmarelo, int tempoVerde) {
        this.tempoVermelho = tempoVermelho;
        this.tempoAmarelo = tempoAmarelo;
        this.tempoVerde = tempoVerde;
    }

    public int getTempoVermelho() {
        return tempoVermelho;
    }

    public int getTempoAmarelo() {
        return tempoAmarelo;
    }

    public int getTempoVerde() {
        return tempoVerde;
    }

}