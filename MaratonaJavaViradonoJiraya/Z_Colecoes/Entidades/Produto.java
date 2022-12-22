package Z_Colecoes.Entidades;
import java.util.Objects;

public class Produto implements Comparable<Produto>{
    private String serialNumber, nome;
    private Double preco;
    private int quantidade;

    public Produto(String serialNumber, String nome, double preco) {
        this.serialNumber = serialNumber;
        this.nome = nome;
        this.preco = preco;
    }
    public Produto(String serialNumber, String nome, Double preco, int quantidade) {
        this.serialNumber = serialNumber;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getSerialNumber() {return serialNumber;}
    public String getNome() {return nome;}
    public Double getPreco() {return preco;}
    public int getQuantidade() {return quantidade;}

    public void setSerialNumber(String serialNumber) {this.serialNumber = serialNumber;}
    public void setNome(String nome) {this.nome = nome;}
    public void setPreco(Double preco) {this.preco = preco;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

    @Override
    public String toString() {
        return "Produto{"+serialNumber+", "+nome+", "+preco+", "+quantidade+'}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;
        return Objects.equals(serialNumber, produto.serialNumber);
    }

    public int hashCode() {return serialNumber != null ? serialNumber.hashCode() : 0;}
    public int compareTo(Produto outroObjeto) {return this.preco.compareTo(outroObjeto.getPreco());}
}
