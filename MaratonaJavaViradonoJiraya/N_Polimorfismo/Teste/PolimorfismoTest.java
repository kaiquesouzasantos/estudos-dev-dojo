package N_Polimorfismo.Teste;
import N_Polimorfismo.Entidades.*;

public class PolimorfismoTest {
    public static void main(String[] args) {
        Gerente gerente = new Gerente("Oswaldo", 5000, 2000);
        Vendedor vendedor = new Vendedor("Yuri",2000,20000);
        RelatorioPagamento relatorio =  new RelatorioPagamento();

        relatorio.relatorioPagamentoGenerico(gerente);
        System.out.println("---------------------------");
        relatorio.relatorioPagamentoGenerico(vendedor);
    }
}
