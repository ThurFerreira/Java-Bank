package Entidades.Cliente;

import java.io.Serializable;
import java.util.Date;
import DataObjects.*;
import Entidades.Pessoa;

public class Cliente extends Pessoa implements Serializable {
    private String estadoCivil;
    private String escolaridade;

    public Cliente(String cpf, String nome, Endereco endereco, String estadoCivil, String escolaridade, Date dataNascimento) {
        super(cpf, nome, endereco, dataNascimento);
        this.estadoCivil = estadoCivil;
        this.escolaridade = escolaridade;
    }

    public Cliente(String nome, String cpf){
        super(cpf, nome, null, null);
        this.estadoCivil = null;
        this.escolaridade = null;
    }

    public Cliente(){
        super(null, null, null, null);
        this.estadoCivil = null;
        this.escolaridade = null;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

      @Override
    public String toString() {
        return super.toString() +
                "estadoCivil='" + estadoCivil + '\'' +
                ", escolaridade='" + escolaridade + '\'' +
                "}\n";
    }
}
