package ZA_Generics.Teste;

abstract class Animal{ public abstract void consulta();}

class Cachorro extends Animal{
    @Override
    public void consulta(){System.out.println("Consultanto Cachorro");}
}

class Gato extends Animal{
    @Override
    public void consulta() {System.out.println("Consultanto Gato");}
}

public class TesteWildcard {
    public static void main(String[] args) {
        Cachorro[] cachorros = {new Cachorro(), new Cachorro()};
        Gato[] gatos = {new Gato(), new Gato()};

        printConsulta(cachorros);
        printConsulta(gatos);

        // equivalente aos arrays acima -> recurso do polimorfismo
        Animal[] animal = {new Gato(), new Cachorro()};
        printConsulta(animal);
    }

    private static void printConsulta(Animal[] animals){
        for(Animal animal:animals){
            animal.consulta();
        }
    }
}


