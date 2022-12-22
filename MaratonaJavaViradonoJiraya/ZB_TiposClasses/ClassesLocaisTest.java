package ZB_TiposClasses;

public class ClassesLocaisTest {
    private String nome = " Kaique";

    // acesso -> o metodo executa o objeto de forma interna
    public void fazAlgumaCoisa() {
        String sobrenome = "Souza";

        class Interna {
            public void imprimeNomeExterno() {
                System.out.println(nome);
            }
        }

        Interna in = new Interna();
        in.imprimeNomeExterno();
    }

    public static void main(String[] args) {
        ClassesLocaisTest externa = new ClassesLocaisTest();
        externa.fazAlgumaCoisa();
    }
}
