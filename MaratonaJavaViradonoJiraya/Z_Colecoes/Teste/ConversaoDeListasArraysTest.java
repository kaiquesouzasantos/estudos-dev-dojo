package Z_Colecoes.Teste;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConversaoDeListasArraysTest {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>(List.of(
                2, 0, 4, 3
        ));

        Integer[] numerosArray = new Integer[numeros.size()];
        // list.toArrau(<array_vazio>) -> converte para um array
        numeros.toArray(numerosArray);

        System.out.println(numeros);
        // Arrays.toString(<arrau>) -> converte para string
        System.out.println(Arrays.toString(numerosArray));

        System.out.println("-----------------------");

        Integer[] numerosArray2 = new Integer[]{10, 9, 8, 7};
        // Arrays.asList(<array>) -> converte arrau para uma list
        List<Integer> numeros2 = Arrays.asList(numerosArray2);
        // list.addAll(<array>) -> copia todos os valores contidos no array
        List<Integer> numeros3 = new ArrayList<>(Arrays.asList(numerosArray2));

        numeros2.set(0,1);
        numeros3.add(10);
        System.out.println(Arrays.toString(numerosArray2));
        System.out.println(numeros2);
        System.out.println(numeros3);
    }
}
