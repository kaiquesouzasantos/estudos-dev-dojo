package ZA_Generics.Entidades;

public class Barco {
    private String nome;

    public Barco(String nome) {this.nome = nome;}

    @Override
    public String toString() {
        return "Carro{" + "nome='" + nome + '\'' +'}';
    }
}
