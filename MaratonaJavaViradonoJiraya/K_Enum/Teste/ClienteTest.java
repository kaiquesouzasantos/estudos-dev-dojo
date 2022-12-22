package K_Enum.Teste;
import K_Enum.Entidades.*;

public class ClienteTest {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Anna", TipoCliente.PESSOA_JURIDICA, Cliente.TipoPagamento.APRAZO);

        System.out.println(cliente.getTipoCliente().getTipo());
        System.out.println(cliente.getTipoCliente().getNome());
        System.out.println(cliente.getTipoPagamento());

        System.out.println(cliente);
    }
}
