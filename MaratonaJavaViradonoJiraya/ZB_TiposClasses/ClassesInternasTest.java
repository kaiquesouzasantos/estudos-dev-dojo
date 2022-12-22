package ZB_TiposClasses;

public class ClassesInternasTest {
    private String nome = "Kaique Souza Santos";

    // acesso -> classePrincipal.classeInterna.<metodo>
    class Interna {
        public void verClasseExterna() {
            System.out.println(nome);
            System.out.println(this);
            System.out.println(ClassesInternasTest.this);
        }
    }

    public static void main(String[] args) {
        ClassesInternasTest externa = new ClassesInternasTest();
        ClassesInternasTest.Interna in = externa.new Interna();
        ClassesInternasTest.Interna in2 = new ClassesInternasTest().new Interna();
        in.verClasseExterna();
    }
}
