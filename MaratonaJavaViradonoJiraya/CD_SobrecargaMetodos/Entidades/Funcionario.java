package CD_SobrecargaMetodos.Entidades;

public class Funcionario {
    private String nome, cpf, rg;
    private double salario;

    public Funcionario(){}
    public Funcionario(String nome, String cpf, double salario, String rg){
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.rg = rg;
    }
    public Funcionario(String nome, String cpf, double salario){
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    public void setNome(String nome) {this.nome = nome;}
    public void setRg(String rg){this.rg = rg;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setSalario(double salario) {this.salario = salario;}

    public String getNome() {return this.nome;}
    public String getCpf() {return this.cpf;}
    public String getRg(){return this.rg;}
    public double getSalario() {return this.salario;}
}
