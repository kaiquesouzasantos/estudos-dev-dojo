package ZC_Lambdas.Entidades;

public class Carro {
    private String nome = "Gol", cor;
    private int ano;

    public Carro(String cor, int ano) {
        this.cor = cor;
        this.ano = ano;
    }

    public String getNome() {return nome;}
    public String getCor() {return cor;}
    public int getAno() {return ano;}

    public void setNome(String nome) {this.nome = nome;}
    public void setCor(String cor) {this.cor = cor;}
    public void setAno(int ano) {this.ano = ano;}

    @Override
    public String toString() {
        return "Carro{" +
                "nome='" + nome + '\'' +
                ", cor='" + cor + '\'' +
                ", ano=" + ano +
                '}';
    }
}
