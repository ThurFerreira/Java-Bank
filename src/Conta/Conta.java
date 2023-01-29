package Conta;

import java.util.*;

import DataObjects.Agencia;
import DataObjects.CurrentDate;
import DataObjects.TransacaoBancaria;
import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import Exceptions.ValorInvalidoException;

import java.io.Serializable;


public abstract class Conta implements TransacoesBancarias, Serializable {

    protected int senha;
    protected int isActive; //1 for active, 0 for deactivate
    protected int numeroConta;
    protected double saldoAtual;
    protected Date dataAberturaConta;
    protected String ultimaMovimentacao;
    protected Agencia agencia;
    protected List<TransacaoBancaria> historico;

    public Conta(int numeroConta, int senha, Agencia agencia) {
        this.senha = senha;
        this.isActive = 1;
        this.numeroConta = numeroConta;
        this.saldoAtual = 0d;
        this.dataAberturaConta = CurrentDate.getCurrentDate();
        historico = new ArrayList<TransacaoBancaria>();
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getIsActive() {
        return isActive;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public Double getSaldoAtual() {
        return saldoAtual;
    }

    public String getDataAberturaConta() {
        return CurrentDate.showDate(this.dataAberturaConta);
    }

    public String getUltimaMovimentacao() {
        return this.ultimaMovimentacao;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public List<TransacaoBancaria> getHistorico(){
        return historico;
    }

    @Override
    public void sacar(int senha, double valor, String meio) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();
        confirmaSenha(senha);
        verificaSaldo(valor);

        //verificando valor a ser sacado
        if(valor > 0.0){
            this.saldoAtual -= valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 1, meio);
            historico.add(novaTransacao);
        }else{
            throw new ValorInvalidoException();
        }

    }

    @Override
    public void depositar(int senha, double valor, String meio) throws SenhaIncorretaException, ContaDesativadaException, ValorInvalidoException{
        isActive();
        confirmaSenha(senha);

        //verificando valor a ser depositado
        if(valor > 0.0){
            this.saldoAtual += valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 2, meio);
            historico.add(novaTransacao);

        }else{
            throw new ValorInvalidoException();
        }
    }

    @Override
    public int consultarSaldo(int senha, String meio) throws SenhaIncorretaException, ContaDesativadaException {
        isActive();
        confirmaSenha(senha);

        System.out.printf("R$ %.2f\n", this.saldoAtual);
        TransacaoBancaria novaTransacao = new TransacaoBancaria(0, 3, meio);
        historico.add(novaTransacao);

        return 0;
    }

    @Override
    public void efetuarPagamento(int senha, double valor, String meio, Date dataPagamento) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();
        confirmaSenha(senha);
        verificaSaldo(valor);

        if(valor > 0d){
            this.saldoAtual -= valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 4, meio, dataPagamento);
            historico.add(novaTransacao);

        }else{
            throw new ValorInvalidoException();
        }

    }

    protected void confirmaSenha(int senha) throws SenhaIncorretaException {
        if (senha != this.senha)
            throw new SenhaIncorretaException();
    }

    protected void isActive() throws ContaDesativadaException{

        if(this.isActive == 0){
            throw new ContaDesativadaException();
        }
    }

    protected void verificaSaldo(double valor) throws SemSaldoException{

        if(this.saldoAtual < valor){
            throw new SemSaldoException();
        }
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "senha=" + senha +
                ", isActive=" + isActive +
                ", numeroConta=" + numeroConta +
                ", saldoAtual=" + saldoAtual +
                ", dataAberturaConta=" + dataAberturaConta +
                ", ultimaMovimentacao='" + ultimaMovimentacao + '\'' +
                ", agencia=" + agencia +
                ", historico=" + historico +
                '}';
    }
}
