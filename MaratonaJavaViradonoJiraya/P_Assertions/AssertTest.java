package P_Assertions;

public class AssertTest {
    public static void main(String[] args) {
        /*
        assert -> testador, funciona como um 'throw new' só que para o ambiente de testes
        assert expressao1 : expressao2; -> condicao, str da exception, caso a condição seja verdadeira, ele solta a exceção

         */

        diasDaSemana(9);
        calculaSalario(-2);
    }

    private static void calculaSalario(double salario) {
        assert salario < 0;
    }

    public static void diasDaSemana(int dia) {
        switch (dia) {
            case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                break;
            default:
                assert false;
        }
    }
}