package H_Heranca.Entidades;

public class Funcionario extends Pessoa {
    private double salario;

    public Funcionario(String nome) {
        super(nome);
    }
    public Funcionario(String nome, String cpf) {
        super(nome, cpf);
    }
    public Funcionario(String nome, String cpf, Endereco endereco) {
        super(nome, cpf, endereco);
    }
    public Funcionario(String nome, double salario) {
        super(nome);
        this.salario = salario;
    }
    public Funcionario(String nome, String cpf, double salario) {
        super(nome, cpf);
        this.salario = salario;
    }
    public Funcionario(String nome, String cpf, Endereco endereco, double salario) {
        super(nome, cpf, endereco);
        this.salario = salario;
    }

    @Override
    public String retornaValores() {
        return "Funcionario{"+nome+", "+cpf+", R$"+salario+", "+endereco+"}";
    }

    public double getSalario() {return salario;}
    public void setSalario(double salario) {this.salario = salario;}

    @Override
    public String toString(){
        return retornaValores();
    }
}
