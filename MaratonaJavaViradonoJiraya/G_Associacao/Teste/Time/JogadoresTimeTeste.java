package G_Associacao.Teste.Time;
import G_Associacao.Entidades.Time.*;

public class JogadoresTimeTeste {
    public static void main(String[] args) {
        Jogador jogador1 = new Jogador("Cafu");
        Jogador jogador2 = new Jogador("Neymar");

        Time time = new Time("Brasil", new Jogador[]{jogador1, jogador2});

        jogador1.setTime(time);
        jogador2.setTime(time);

        System.out.println(time);
    }
}
