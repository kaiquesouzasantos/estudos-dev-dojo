package G_Associacao.Entidades.Time;

import java.util.Arrays;

public class Time {
    private String nome;
    private Jogador[] jogadores;

    public Time(String nome){this.nome = nome;}
    public Time(String nome, Jogador[] jogadores) { // 1 time para N jogadores
        this.nome = nome;
        this.jogadores = jogadores;
    }

    public String getNome() {return nome;}
    public Jogador[] getJogadores() {return jogadores;}

    public void setNome(String nome) {this.nome = nome;}
    public void setJogadores(Jogador[] jogadores) {this.jogadores = jogadores;}

    @Override
    public String toString(){
        return "Time{"+nome+", "+Arrays.toString(jogadores)+"}";
    }
}
