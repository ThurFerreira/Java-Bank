package Entidades.Funcionario;
import DataObjects.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Gerente extends Funcionario implements Serializable {

    private Date dataIngressoNaCarreira;
    private int cursoFormacao;//1 for true, 0 for false

    public Gerente(String cpf, String nome, String rg, Date dataNascimento, Endereco endereco, String numeroCTPS, char sexo, String estadoCivil, double salario, Date dataIngressoNaCarreira, int cursoFormacao) {
        super(cpf, nome, rg, dataNascimento, endereco, numeroCTPS, sexo, estadoCivil, "Gerente", salario);
        this.dataIngressoNaCarreira = dataIngressoNaCarreira;
        this.cursoFormacao = cursoFormacao;
    }

    public Date getDataIngressoNaCarreira() {
        return dataIngressoNaCarreira;
    }

    public void setDataIngressoNaCarreira(Date dataIngressoNaCarreira) {
        this.dataIngressoNaCarreira = dataIngressoNaCarreira;
    }

    public int getCursoFormacao() {
        return cursoFormacao;
    }

    public void setCursoFormacao(int cursoFormacao) {
        this.cursoFormacao = cursoFormacao;
    }
}
