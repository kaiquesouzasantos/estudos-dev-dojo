package CD_SobrecargaMetodos.Entidades;

import java.util.Arrays;

public class Estudante {
    private String matricula, nome, dataMatricula;
    private double[] notas;

    public Estudante() {
        System.out.println("construtor default");
    }
    public Estudante(String matricula, String nome, double[] notas) {
        this.matricula = matricula;
        this.nome = nome;
        this.notas = notas;
    }
    public Estudante(String matricula, String nome, double[] notas, String dataMatricula) {
        this(matricula, nome, notas);
        this.dataMatricula = dataMatricula;
    }

    @Override
    public String toString() {
        return "Estudante{"+nome+", "+matricula+", "+dataMatricula+", "+Arrays.toString(notas) +"}";
    }

    public String getDataMatricula() {return dataMatricula;}
    public String getMatricula() {return matricula;}
    public String getNome() {return nome;}
    public double[] getNotas() {return notas;}

    public void setDataMatricula(String dataMatricula) {this.dataMatricula = dataMatricula;}
    public void setMatricula(String matricula) {this.matricula = matricula;}
    public void setNome(String nome) {this.nome = nome;}
    public void setNotas(double[] notas) {this.notas = notas;}
}