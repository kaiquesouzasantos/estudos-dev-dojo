package G_Associacao.Entidades.Escola;
import java.util.Arrays;

public class Escola {
    private String nome;
    private Professor[] professores;

    public Escola(String nome) {this.nome = nome;}
    public Escola(String nome, Professor[] professores) {
        this.nome = nome;
        this.professores = professores;
    }

    @Override
    public String toString() {
        return "Escola{"+nome+Arrays.toString(professores)+"}";
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Professor[] getProfessores() {return professores;}
    public void setProfessores(Professor[] professores) {this.professores = professores;}
}
