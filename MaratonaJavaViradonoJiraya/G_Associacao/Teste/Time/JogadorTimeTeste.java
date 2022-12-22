package G_Associacao.Teste.Time;
import G_Associacao.Entidades.Time.*;

public class JogadorTimeTeste {
    public static void main(String[] args) {
        // 1 jogador para 1 time
        Jogador jogador = new Jogador("CR7");
        Time time = new Time("Selecao Brasileira");
        jogador.setTime(time);

        System.out.println(jogador);
    }
}
