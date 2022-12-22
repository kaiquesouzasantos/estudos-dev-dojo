package G_Associacao.Entidades.Seminario;

public class Estudante {
    private String nome;
    private int idade;
    private Seminario seminario;

    public Estudante(){}
    public Estudante(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }
    public Estudante(String nome, int idade, Seminario seminario) {
        this.nome = nome;
        this.idade = idade;
        this.seminario = seminario;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public int getIdade() {return idade;}
    public void setIdade(int idade) {this.idade = idade;}

    public Seminario getSeminario() {return seminario;}
    public void setSeminario(Seminario seminario) {this.seminario = seminario;}
}
