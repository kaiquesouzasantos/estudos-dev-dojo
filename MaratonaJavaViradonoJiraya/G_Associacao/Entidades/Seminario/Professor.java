package G_Associacao.Entidades.Seminario;

public class Professor {
    private String nome, especialidade;
    private Seminario[] seminarios;

    public Professor(){}
    public Professor(String nome) {this.nome = nome;}
    public Professor(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }
    public Professor(String nome, String especialidade, Seminario[] seminarios){
        this.nome = nome;
        this.especialidade = especialidade;
        this.seminarios = seminarios;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getEspecialidade() {return especialidade;}
    public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}

    public Seminario[] getSeminarios() {return seminarios;}
    public void setSeminarios(Seminario[] seminarios) {this.seminarios = seminarios;}

    @Override
    public String toString(){
        String saida = "PROFESSOR: "+nome+"";

        if(seminarios != null) {
            for (Seminario seminario : seminarios) {
                saida += "\nSEMINARIO: " + seminario.getTitulo();
                saida += "\nLOCAL: " + seminario.getLocal().getEndereco();

                if (seminario.getEstudantes() == null) continue; // estudantes == null, pula o trecho abaixo

                saida += "\n** ALUNOS **";
                for (Estudante estudantes : seminario.getEstudantes())
                    saida += "\nALUNO: " + estudantes.getNome() + " | IDADE: " + estudantes.getIdade();
            }
        }
        return saida;
    }
}
