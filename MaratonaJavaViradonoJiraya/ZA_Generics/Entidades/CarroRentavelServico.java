package ZA_Generics.Entidades;

import java.util.ArrayList;
import java.util.List;

public class CarroRentavelServico {
    private List<Carro> carrosDisponiveis = new ArrayList<>(List.of(new Carro("BMW"), new Carro("Mercedes")));

    public Carro buscarCarroDisponivel(){
        System.out.println("Buscando carro disponival....");
        Carro carro = carrosDisponiveis.remove(0);
        System.out.println("Alugando carro:"+carro);
        System.out.println("Carros disponiveis: "+carrosDisponiveis);
        return carro;
    }

    public void retornaCarroAlugado(Carro carro){
        System.out.println("Devolvendo carro"+carro);
        carrosDisponiveis.add(carro);
        System.out.println("Carros disponiveis para aluguel: "+carrosDisponiveis);
    }
}
