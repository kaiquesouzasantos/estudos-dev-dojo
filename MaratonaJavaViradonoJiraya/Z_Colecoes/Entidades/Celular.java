package Z_Colecoes.Entidades;

public class Celular {
    private String nome, IMEI;

    public Celular(String nome, String IMEI) {
        this.nome = nome;
        this.IMEI = IMEI;
    }

    public String getNome() {return nome;}
    public String getIMEI() {return IMEI;}

    public void setNome(String nome) {this.nome = nome;}
    public void setIMEI(String IMEI) {this.IMEI = IMEI;}

    @Override
    public String toString() {
        return "Celular{"+nome+", "+IMEI+'}';
    }

    public int hashCode() {return IMEI != null ? IMEI.hashCode() : 1;}

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;

        Celular outroCelular = (Celular) obj;
        return IMEI != null && IMEI.equals(outroCelular.getIMEI());
    }
}
