package Z_Colecoes.Teste;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new LinkedHashMap<>(Map.of(
                "teklado", "teclado",
                "mouze", "mouse",
                "vc", "você",
                "Meza", "mesa"
        ));

        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}
