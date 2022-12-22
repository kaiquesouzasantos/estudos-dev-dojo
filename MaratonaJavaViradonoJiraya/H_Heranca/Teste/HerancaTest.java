package H_Heranca.Teste;
import H_Heranca.Entidades.*;

public class HerancaTest {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa(
                "Furiosa", "5148.4785.795-87",
                new Endereco("Av.Souza Ramos", "Cidade Tiradentes"));
        System.out.println(pessoa);

        System.out.println("-------------------------------");

        Funcionario funcionario = new Funcionario(
                "Kaique", "5148.4785.795-87",
                new Endereco("Av.Souza Ramos", "Cidade Tiradentes"), 15000);
        System.out.println(funcionario);
    }
}
