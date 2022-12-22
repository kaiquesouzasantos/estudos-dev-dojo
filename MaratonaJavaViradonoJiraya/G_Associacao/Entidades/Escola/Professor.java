package G_Associacao.Entidades.Escola;

public class Professor {
    private String nome;

    public Professor(String nome) {this.nome = nome;}

    public void setNome(String nome) {this.nome = nome;}

    @Override
    public String toString() {return nome;}
}
