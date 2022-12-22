package M_Interfaces.Entidades;

public class Produto implements Tributavel, Transportavel {
    private String nome;
    private double peso, preco, precoFinal, valorFrete;

    public Produto(String nome, double peso, double preco) {
        this.nome = nome;
        this.peso = peso;
        this.preco = preco;
    }

    @Override
    public void calculaImposto() {
        precoFinal = this.preco + (this.preco * IMPOSTO);
    }
    public void calculaFrete() {
        this.valorFrete = this.preco / peso * 0.1;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", peso=" + peso +
                ", preco=" + preco +
                ", precoFinal com Imposto=" + precoFinal +
                ", valorFrete=" + valorFrete +
                '}';
    }

    public String getNome() {return nome;}
    public double getPeso() {return peso;}
    public double getPreco() {return preco;}
    public double getPrecoFinal() {return precoFinal;}
    public double getValorFrete() {return valorFrete;}

    public void setNome(String nome) {this.nome = nome;}
    public void setPeso(double peso) {this.peso = peso;}
    public void setPreco(double preco) {this.preco = preco;}
    public void setPrecoFinal(double precoFinal) {this.precoFinal = precoFinal;}
    public void setValorFrete(double valorFrete) {this.valorFrete = valorFrete;}
}
