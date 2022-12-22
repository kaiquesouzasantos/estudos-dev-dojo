package Z_Colecoes.Teste;
import Z_Colecoes.Entidades.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsumidorMapTest {
    public static void main(String[] args) {
        Map<Consumidor, List<Produto>> map = new HashMap<>();

        List<Produto> produtosCon1 = List.of(
                new Produto("123", "Laptop Lenovo", 2000.0),
                new Produto("321", "Picanha", 26.4)
        );
        List<Produto> produtosCon2 = List.of(
                new Produto("879", "Teclado Razer", 1000.0),
                new Produto("012", "Samsung galaxy S7 64Gb", 3250.5)
        );

        map.put(new Consumidor("William Suane", "122"), produtosCon1);
        map.put(new Consumidor("DevDojo", "321"), produtosCon2);
        map.put(new Consumidor("DevDojo", "321"), produtosCon1);

        for(Map.Entry<Consumidor,List<Produto>> entry : map.entrySet()){
            System.out.println(entry.getKey().getNome());

            for(Produto produto : entry.getValue()){
                System.out.println(produto.getNome());
            }
        }

        System.out.println(map);
    }
}
