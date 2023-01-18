package Funcionario;
import DataObjects.*;

import java.util.Date;

public class Gerente extends Funcionario{

    private String dataIngressoNaCarreira;
    private Agencia[] agencia = new Agencia[1];//vetor de agencias que o gerente trabalha
    private int cursoFormacao;//1 for true, 0 for false

    public Gerente(String cpf, String nome, int rg, Date dataNascimento, Endereco endereco, int numeroCTPS, char sexo, String estadoCivil, String cargo, double salario, String dataIngressoNaCarreira, Agencia[] agencia, int cursoFormacao) {
        super(cpf, nome, rg, dataNascimento, endereco,numeroCTPS, sexo, estadoCivil, cargo, salario);
        this.dataIngressoNaCarreira = dataIngressoNaCarreira;
        this.agencia = agencia;
        this.cursoFormacao = cursoFormacao;
    }
}
