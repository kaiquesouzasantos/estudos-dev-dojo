package A_IntroducaoClasses.Teste;
import A_IntroducaoClasses.Entidades.Carro;

public class CarroTest {
    public static void main(String[] args) {
        Carro carro = new Carro();

        carro.modelo = "Fusca";
        carro.placa = "QWJ1029";
        carro.velocidadeMaxima = 120f;

        System.out.println(carro.placa);
        System.out.println(carro.modelo);
        System.out.println(carro.velocidadeMaxima);
    }
}
