package F_ModificadorEstatico.Teste;
import F_ModificadorEstatico.Entidades.Cliente;

public class ClienteTest {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();

        System.out.println("Exibindo quantidade de parcelas possiveis");
        System.out.println("Quantidade de parcelas: "+Cliente.getParcelas().length);
    }
}
