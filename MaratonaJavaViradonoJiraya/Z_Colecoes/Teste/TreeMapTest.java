package Z_Colecoes.Teste;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
        NavigableMap<String, String> map = new TreeMap<>(Map.of(
                "A", "Letra A",
                "D", "Letra D",
                "B", "Letra B",
                "C", "Letra C"
        ));

        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }

        System.out.println(map.headMap("C",true));
        System.out.println(map.higherEntry("C"));
        System.out.println(map.descendingMap());
    }
}
