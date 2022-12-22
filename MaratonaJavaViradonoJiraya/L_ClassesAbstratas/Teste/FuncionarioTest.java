package L_ClassesAbstratas.Teste;
import L_ClassesAbstratas.Entidades.*;

public class FuncionarioTest {
    public static void main(String[] args) {
        // Pessoa > Funcionario > Vendedor, Gerente
        Gerente gerente = new Gerente("Anna", "11122-2", 2000);
        Vendedor vendedor = new Vendedor("Camila", "22211-4",1500,5000);

        gerente.calculaSalario();
        vendedor.calculaSalario();

        System.out.println(gerente);
        System.out.println(vendedor);
    }
}
