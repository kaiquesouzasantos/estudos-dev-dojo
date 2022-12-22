package B_IntroducaoMetodos.Entidades;

public class Professor {
    public String nome, matricula, rg, cpf;

    public void imprime(){
        System.out.println("----------------------");
        System.out.println(this.cpf);
        System.out.println(this.matricula);
        System.out.println(this.nome);
        System.out.println(this.rg);
    }
}
