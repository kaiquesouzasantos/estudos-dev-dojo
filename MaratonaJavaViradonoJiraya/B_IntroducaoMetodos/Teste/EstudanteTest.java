package B_IntroducaoMetodos.Teste;
import B_IntroducaoMetodos.Entidades.Estudante;

public class EstudanteTest {
    public static void main(String[] args) {
        Estudante estudante = new Estudante();

        estudante.setNome("Kaique");
        estudante.setIdade(10);
        estudante.setNotas(new double[]{3,2,9.5});

        estudante.print();
        estudante.tirarMedia();

        System.out.println("Aprovado? "+estudante.isAprovado());
    }
}
