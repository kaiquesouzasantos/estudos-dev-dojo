package CD_SobrecargaMetodos.Teste;
import CD_SobrecargaMetodos.Entidades.Estudante;

public class EstudanteTest {
    public static void main(String[] args) {
        Estudante estudante = new Estudante(
                "12212", "Carlitos", new double[]{5,7,9},"13/04/2022");

        System.out.println(estudante);
    }
}
