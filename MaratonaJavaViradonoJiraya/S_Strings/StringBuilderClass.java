package S_Strings;

public class StringBuilderClass {
    public static void main(String[] args) {
        // StringBuilder -> desempenho, concatena qualquer dado (objeto ou primitivo)
        StringBuilder stringBuilder = new StringBuilder(16); // parametro -> limite de caracteres aceitos

        // append() -> concatenação, converte todos os dados para seu tipo
        stringBuilder.append("Kaique");
        // substring(<init>, <end>) -> retorna o periodo respectivo
        System.out.println(stringBuilder.substring(0, 2));
        // reverse() -> inverte a sequencia de caracteres
        System.out.println(stringBuilder.reverse());
        // capacity() -> capacidade de caracteres aceitos
        System.out.println(stringBuilder.capacity());
        // insert(<indice>, <valor>) -> adiciona o valor a partir do indice
        stringBuilder.insert(0, "weq");
        // replace(<init>, <end>, <valor>) -> substitui o periodo indicado pelo valor
        stringBuilder.replace(1,2,"J");
        // delete() -> deleta o periodo indicado
        stringBuilder.delete(0, 3);

        System.out.println(stringBuilder);
    }
}
