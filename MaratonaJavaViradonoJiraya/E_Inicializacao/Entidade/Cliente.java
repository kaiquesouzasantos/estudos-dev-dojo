package E_Inicializacao.Entidade;

public class Cliente {
    public Cliente(){}

    // atribuição simplificada de um valor final
    private final int[] parcelas;
    {
        parcelas = new int[100];

        System.out.println("Dentro do bloco de inicializacao");
        for(int i = 1; i <= 100; i++){
            parcelas[i-1] = i;
        }
    }

    public int[] getParcelas() {return parcelas;}
}
