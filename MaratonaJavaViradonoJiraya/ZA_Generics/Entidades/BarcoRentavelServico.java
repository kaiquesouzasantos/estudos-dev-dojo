package ZA_Generics.Entidades;
import java.util.ArrayList;
import java.util.List;

public class BarcoRentavelServico {
    private List<Barco> barcosDisponiveis = new ArrayList<>(
            List.of(new Barco("Lancha"), new Barco("Canoa"))
    );

    public Barco buscarBarcoDisponivel(){
        System.out.println("Buscando Barco disponival....");
        Barco barco = barcosDisponiveis.remove(0);
        System.out.println("Alugando Barco:"+barco);
        System.out.println("Barcos disponiveis: "+barcosDisponiveis);
        return barco;
    }

    public void retornaBarcoAlugado(Barco barco){
        System.out.println("Devolvendo Barco"+barco);
        barcosDisponiveis.add(barco);
        System.out.println("Barcos disponiveis para aluguel: "+barcosDisponiveis);
    }
}
