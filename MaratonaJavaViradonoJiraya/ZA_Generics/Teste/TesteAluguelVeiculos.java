package ZA_Generics.Teste;

import ZA_Generics.Entidades.Barco;
import ZA_Generics.Entidades.BarcoRentavelServico;
import ZA_Generics.Entidades.Carro;
import ZA_Generics.Entidades.CarroRentavelServico;

public class TesteAluguelVeiculos {
    public static void main(String[] args) {
        chamarCarro();
        chamarBarco();
    }

    public static void chamarCarro(){
        CarroRentavelServico carroRentavelServico = new CarroRentavelServico();
        Carro carro = carroRentavelServico.buscarCarroDisponivel();
        System.out.println("usando o carro por um mes...");
        carroRentavelServico.retornaCarroAlugado(carro);
    }

    public static void chamarBarco(){
        BarcoRentavelServico barcoRentavelServico = new BarcoRentavelServico();
        Barco barco = barcoRentavelServico.buscarBarcoDisponivel();
        System.out.println("usando o barco por um mes...");
        barcoRentavelServico.retornaBarcoAlugado(barco);
    }
}
