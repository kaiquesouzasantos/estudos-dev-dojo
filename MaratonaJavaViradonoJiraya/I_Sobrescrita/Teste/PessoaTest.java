package I_Sobrescrita.Teste;
import I_Sobrescrita.Entidade.Pessoa;

public class PessoaTest {
    public static void main(String[] args) {
        Pessoa pessoa1 = new Pessoa("Joaquina na esquina", 300);
        System.out.println(pessoa1);

        Pessoa pessoa2 = new Pessoa("Joao na esquina", 200);
        System.out.println(pessoa2);
    }
}
