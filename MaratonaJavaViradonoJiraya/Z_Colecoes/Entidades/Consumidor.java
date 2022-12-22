package Z_Colecoes.Entidades;
import java.util.Objects;

public class Consumidor {
    private String nome, cpf;

    public Consumidor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {return nome;}
    public String getCpf() {return cpf;}

    public void setNome(String nome) {this.nome = nome;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    @Override
    public String toString() {
        return "Cosumidor{"+nome+", "+cpf+"}";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consumidor consumidor = (Consumidor) o;
        return Objects.equals(cpf, consumidor.cpf);
    }

    public int hashCode() {return cpf != null ? cpf.hashCode() : 0;}
}
