package G_Associacao.Entidades.Seminario;

public class Seminario {
    private String titulo;
    private Estudante[] estudantes;
    private Local local;

    public Seminario(){}
    public Seminario(String titulo){
        this.titulo = titulo;
    }
    public Seminario(String titulo, Estudante[] estudantes) {
        this.titulo = titulo;
        this.estudantes = estudantes;
    }
    public Seminario(String titulo, Estudante[] estudantes, Local local) {
        this.titulo = titulo;
        this.estudantes = estudantes;
        this.local = local;
    }

    public Estudante[] getEstudantes() {return estudantes;}
    public void setEstudantes(Estudante[] estudantes) {this.estudantes = estudantes;}

    public Local getLocal() {return local;}
    public void setLocal(Local local) {this.local = local;}

    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
}
