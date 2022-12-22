package J_ModificadorFinal.Entidades;

public class Carro {
    // final -> permite somente uma atribuição de valor
    // uma vez que tenha seu valor definido, toma-a como verdade
    public final double VELOCIDADE_FINAL = 250;
    public final Comprador COMPRADOR = new Comprador();

    private String nome;
    private String marca;

    public Carro(String nome, String marca) {
        this.nome = nome;
        this.marca = marca;
        this.COMPRADOR.setNome("Josefino Cabeça de Limao");
    }

    @Override
    public String toString() {
        return "Carro{" +
                "nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}
}
