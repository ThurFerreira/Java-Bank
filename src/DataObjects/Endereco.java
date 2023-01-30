package DataObjects;

import java.io.Serializable;
import java.util.Date;

public class Endereco implements Serializable {
    private String cidade;
    private String estado;
    private String bairro;
    private  String rua;

    public Endereco(String bairro, String cidade, String estado){
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
    }

    public Endereco(String rua,String bairro, String cidade, String estado){
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.rua = rua;
    }


    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return  bairro + ", " + cidade + " - " + estado; //bairro, cidade - estado
    }
}
