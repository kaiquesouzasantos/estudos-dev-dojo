package M_Interfaces.Teste;
import M_Interfaces.Entidades.Produto;

public class TesteProduto {
    public static void main(String[] args) {
        Produto produto = new Produto("Notebook",4,3000);

        produto.calculaImposto();
        produto.calculaFrete();

        System.out.println(produto);
    }
}
