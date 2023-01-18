package Cliente;

import java.text.SimpleDateFormat;
import java.util.Date;
import DataObjects.*;

public class Cliente extends Pessoa{
    private String estadoCivil;
    private String escolaridade;
    private Agencia agencia;

    public Cliente(){
        super();

    }

    public Cliente(String cpf, String nome){
        super(cpf, nome);

    }
    public Cliente(String cpf, String nome, Endereco endereco, String estadoCivil, String escolaridade, Date dataNascimento, Agencia agencia) {
        super(cpf, nome, endereco, dataNascimento);
        this.estadoCivil = estadoCivil;
        this.escolaridade = escolaridade;
        this.agencia = agencia;
    }
}
