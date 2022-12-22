package ZB_TiposClasses;

class Externa {
    static class Interna{
        public void imprimir(){
            System.out.println("oi");
        }
    }
}

public class ClassesInternasEstaticasTest {
    public static void main(String[] args) {
        Externa.Interna teste = new Externa.Interna();
        teste.imprimir();

        Interna2 teste2 = new Interna2();
        teste2.imprimir();
    }

    static class Interna2{
        public void imprimir(){
            System.out.println("oi dentro do interna 2");
        }
    }
}
