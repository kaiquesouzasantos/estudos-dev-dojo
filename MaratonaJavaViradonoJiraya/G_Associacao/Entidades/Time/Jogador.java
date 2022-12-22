package G_Associacao.Entidades.Time;

public class Jogador {
    private String nome;
    private Time time;

    public Jogador(String nome){
        this.nome = nome;
    }

    public void setNome(String nome){this.nome = nome;}

    public Time getTime() {return time;}
    public void setTime(Time time) {this.time = time;}

    @Override
    public String toString(){
        return "Jogador{"+nome+", "+time+"}";
    }
}
