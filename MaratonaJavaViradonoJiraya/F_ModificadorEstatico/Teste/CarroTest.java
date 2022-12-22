package F_ModificadorEstatico.Teste;
import F_ModificadorEstatico.Entidades.Carro;

public class CarroTest {
    public static void main(String[] args) {
        // altera a velocidadeLimite de todos os carros
        Carro.setVelocidadeLimite(220);
        System.out.println(Carro.getVelocidadeLimite());

        Carro carro1 = new Carro("BMW", 280);
        Carro carro2 = new Carro("Audi", 275);
        Carro carro3 = new Carro("Mercedes", 290);

        System.out.println(carro1);
        System.out.println(carro2);
        System.out.println(carro3);
    }
}
