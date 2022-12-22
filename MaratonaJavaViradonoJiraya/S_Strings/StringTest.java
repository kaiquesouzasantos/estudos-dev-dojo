package S_Strings;

public class StringTest {
    public static void main(String[] args) {
        String nome = "Kaique";
        nome = nome.concat(" Suane");
        String nome2 = "Kaique";
        String nome3 = new String("Catarina");

        // METODOS CONTIDOS NA STRING
        String teste = " Baki";
        String teste2 = "     0123456789       ";
        System.out.println(teste.charAt(3));
        System.out.println(teste.equalsIgnoreCase(teste2));
        System.out.println(teste.length());
        System.out.println(teste2.replace('a','o'));
        System.out.println(teste2.toLowerCase());
        System.out.println(teste2.toUpperCase());
        System.out.println(teste2.substring(0,5));
        System.out.println(teste2.trim());
    }
}
