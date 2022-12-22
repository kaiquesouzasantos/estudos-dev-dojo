package F_ModificadorEstatico.Entidades;

public class Carro {
    private String nome;
    private double velocidadeMaxima;
    private static double velocidadeLimite = 0;

    public Carro() {}
    public Carro(String nome, double velocidadeMaxima) {
        this.nome = nome;
        this.velocidadeMaxima = velocidadeMaxima;
    }

    // static só executa static e comandos internos
    public static void setVelocidadeLimite(double velocidadeLimite) {
        Carro.velocidadeLimite = velocidadeLimite;
    }
    public static double getVelocidadeLimite() {return velocidadeLimite;}

    public String getNome() {return nome;}
    public double getVelocidadeMaxima() {return velocidadeMaxima;}

    public void setNome(String nome) {this.nome = nome;}
    public void setVelocidadeMaxima(double velocidadeMaxima) {this.velocidadeMaxima = velocidadeMaxima;}

    @Override
    public String toString() {
        return "Carro{"+nome+", "+velocidadeMaxima+", "+velocidadeLimite+'}';
    }
}
