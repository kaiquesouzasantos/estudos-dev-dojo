package G_Associacao.Teste.Escola;
import G_Associacao.Entidades.Escola.*;

public class EscolaTeste {
    public static void main(String[] args) {
        Professor professor1 = new Professor("Jiraya");
        Professor professor2 = new Professor("Danone");
        Professor professor3 = new Professor("Furiosa");

        Professor[] professores = {professor1, professor2, professor3};
        Escola escola = new Escola("Toshiba", professores); // 1 escola para N professores

        System.out.println(escola);
    }
}
