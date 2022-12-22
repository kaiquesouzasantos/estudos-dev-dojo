package J_ModificadorFinal.Teste;
import J_ModificadorFinal.Entidades.Carro;

public class CarroTest {
    public static void main(String[] args) {
        Carro carro = new Carro("F-150 Raptor", "Ford");

        System.out.println(carro.COMPRADOR);
        System.out.println(carro.VELOCIDADE_FINAL);
        System.out.println(carro);
    }
}
