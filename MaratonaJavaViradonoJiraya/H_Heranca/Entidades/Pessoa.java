package H_Heranca.Entidades;

public class Pessoa {
    protected String nome;
    protected String cpf;
    protected Endereco endereco;

    public Pessoa(String nome) {
        this.nome = nome;
    }
    public Pessoa(String nome, String cpf) {
        this(nome);
        this.cpf = cpf;
    }
    public Pessoa(String nome, String cpf, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String retornaValores(){
        return "Pessoa{"+nome+", "+cpf+", "+endereco+'}';
    }

    public String getNome() {return nome;}
    public String getCpf() {return cpf;}
    public Endereco getEndereco() {return endereco;}

    public void setNome(String nome) {this.nome = nome;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}

    @Override
    public String toString() {
        return retornaValores();
    }
}
