package G_Associacao.Teste.Seminario;
import G_Associacao.Entidades.Seminario.*;


public class SeminarioTeste {
    public static void main(String[] args) {
        Local local = new Local("Rua das Laranjeiras");
        Estudante estudante1 = new Estudante("Kaique", 17);
        Estudante estudante2 = new Estudante("Lorena", 17);
        Estudante estudante3 = new Estudante("Karina", 17);
        Professor professor = new Professor("Jiraya", "Java");

        Estudante[] estudantes = {estudante1, estudante2, estudante3};
        Seminario seminario = new Seminario("Codar até o olho sangrar", estudantes, local);

        professor.setSeminarios(new Seminario[]{seminario});

        System.out.println(professor);
    }
}
