package E_Inicializacao.Teste;
import E_Inicializacao.Entidade.*;

public class ClienteTest {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        System.out.println("Exibindo quantidade de parcelas possiveis");

        for(int parcela : cliente.getParcelas()){
            System.out.println(parcela);
        }
    }
}
