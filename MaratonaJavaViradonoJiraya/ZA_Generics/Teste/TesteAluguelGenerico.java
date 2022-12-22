package ZA_Generics.Teste;
import ZA_Generics.Entidades.Barco;
import ZA_Generics.Entidades.Carro;
import ZA_Generics.Entidades.ServicoRentavel;
import java.util.ArrayList;
import java.util.List;

public class TesteAluguelGenerico {
    public static void main(String[] args) {
        List<Carro> carrosDisponiveis = new ArrayList<>(
                List.of(new Carro("BMW"), new Carro("Mercedes"))
        );
        List<Barco> barcosDisponiveis = new ArrayList<>(
                List.of(new Barco("Lancha"), new Barco("Canoa"))
        );

        ServicoRentavel<Carro> rentavelC = new ServicoRentavel<>(carrosDisponiveis);

        Carro carro = rentavelC.buscarObjetoDisponivel();
        System.out.println("Usando o carro por um mes...");
        rentavelC.retornaObjetoAlugado(carro);

        System.out.println("-----------------------");

        ServicoRentavel<Barco> rentavelB = new ServicoRentavel<>(barcosDisponiveis);

        Barco barco = rentavelB.buscarObjetoDisponivel();
        System.out.println("Usando o barco por um mes...");
        rentavelB.retornaObjetoAlugado(barco);
    }
}
