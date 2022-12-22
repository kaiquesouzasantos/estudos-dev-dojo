package L_ClassesAbstratas.Entidades;

public abstract class Funcionario extends Pessoa {
    protected String clt;
    protected double salario;

    public Funcionario() {}

    public Funcionario(String nome, String clt, double salario) {
        this.nome = nome;
        this.clt = clt;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", clt='" + clt + '\'' +
                ", salario=" + salario +
                '}';
    }

    public abstract void calculaSalario();

    public String getClt() {return clt;}
    public double getSalario() {return salario;}

    public void setClt(String clt) {this.clt = clt;}
    public void setSalario(double salario) {this.salario = salario;}
}
