package G_Associacao.Teste.Time;
import G_Associacao.Entidades.Time.Jogador;
import java.util.List;

public class JogadorTeste {
    public static void main(String[] args) {
        List<Jogador> jogadores = List.of(
                new Jogador("Cafu"),
                new Jogador("Neymar"),
                new Jogador("CR7")
        );

        jogadores.forEach(System.out::println);
    }
}
