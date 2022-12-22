package CD_SobrecargaMetodos.Teste;
import CD_SobrecargaMetodos.Entidades.*;

public class FuncionarioTest {
    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario(
                "Chica da Silva", "111.222.333-44", 4500, "122212-9"
        );
        System.out.println(funcionario);

        Funcionario funcionario2 = new Funcionario();
        System.out.println(funcionario2);
    }
}
