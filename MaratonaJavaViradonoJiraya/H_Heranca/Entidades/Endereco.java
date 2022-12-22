package H_Heranca.Entidades;

public class Endereco {
    private String rua, bairro;

    public Endereco() {}
    public Endereco(String rua, String bairro) {
        this.rua = rua;
        this.bairro = bairro;
    }

    public String getRua() {return rua;}
    public String getBairro() {return bairro;}

    public void setRua(String rua) {this.rua = rua;}
    public void setBairro(String bairro) {this.bairro = bairro;}

    @Override
    public String toString() {
        return rua+" - "+bairro;
    }
}
