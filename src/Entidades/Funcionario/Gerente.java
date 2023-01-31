package Entidades.Funcionario;
import DataObjects.*;

import java.io.Serializable;
import java.util.Date;

public class Gerente extends Funcionario implements Serializable, CalculoSalarial {

    private Date dataIngressoNaCarreira;
    private int cursoFormacao;//1 for true, 0 for false
    private static int comissao;

    public Gerente(String cpf, String nome, String rg, Date dataNascimento, Endereco endereco, String numeroCTPS, char sexo, String estadoCivil, double salario, Date dataIngressoNaCarreira, int cursoFormacao) {
        super(cpf, nome, rg, dataNascimento, endereco, numeroCTPS, sexo, estadoCivil, "Gerente", salario);
        this.dataIngressoNaCarreira = dataIngressoNaCarreira;
        this.cursoFormacao = cursoFormacao;
        comissao = 0;
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

    public static int getComissao() {
        return comissao;
    }

    public static void setComissao(int comissao) {
        Gerente.comissao = comissao;
    }

    @Override
    public void calculaSalario(double salario) {
        setSalario(salario + comissao);
    }
}
