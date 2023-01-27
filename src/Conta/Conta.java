package Conta;

import java.util.Date;
import DataObjects.Agencia;
import DataObjects.CurrentDate;
import DataObjects.TransacaoBancaria;
import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import Exceptions.ValorInvalidoException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public abstract class Conta implements TransacoesBancarias, Serializable {

    private int senha;
    private int isActive; //1 for active, 0 for deactivate
    private int numeroConta;
    private Double saldoAtual;
    private Date dataAberturaConta;
    private String ultimaMovimentacao;
    private Agencia agencia;
    private Set<TransacaoBancaria> historico;

    public Conta(int numeroConta, int senha, Agencia agencia) {
        this.senha = senha;
        this.isActive = 1;
        this.numeroConta = numeroConta;
        this.saldoAtual = 0d;
        this.dataAberturaConta = CurrentDate.getCurrentDate();
        historico = new HashSet<TransacaoBancaria>();
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

    @Override
    public void sacar(int senha, int valor, String canal) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();
        confirmaSenha(senha);
        verificaSaldo(valor);

        //verificando valor a ser sacado
        if(valor > 0){
            this.saldoAtual -= valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 1, canal);
            historico.add(novaTransacao);
        }else{
            throw new ValorInvalidoException();
        }

    }

    @Override
    public void depositar(int senha, int valor, String canal) throws SenhaIncorretaException, ContaDesativadaException, ValorInvalidoException{
        isActive();
        confirmaSenha(senha);

        //verificando valor a ser depositado
        if(valor > 0){
            this.saldoAtual += valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 1, canal);
            historico.add(novaTransacao);

        }else{
            throw new ValorInvalidoException();
        }
    }

    @Override
    public int consultarSaldo(int senha, String canal) throws SenhaIncorretaException, ContaDesativadaException {
        isActive();
        confirmaSenha(senha);

        System.out.printf("R$ %.2f\n", this.saldoAtual);
        TransacaoBancaria novaTransacao = new TransacaoBancaria(0, 1, canal);
        historico.add(novaTransacao);

        return 0;
    }

    @Override
    public void efeturarPagamento(int senha, int valor, String canal) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();
        confirmaSenha(senha);
        verificaSaldo(valor);

        if(valor > 0){
            this.saldoAtual += valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 1, canal);
            historico.add(novaTransacao);

        }else{
            throw new ValorInvalidoException();
        }

    }

    private void confirmaSenha(int senha) throws SenhaIncorretaException {
        confirmaSenha(senha);

        if (senha != this.senha)
            throw new SenhaIncorretaException();
    }

    private void isActive() throws ContaDesativadaException{

        if(this.isActive == 0){
            throw new ContaDesativadaException();
        }
    }

    private void verificaSaldo(int valor) throws SemSaldoException{

        if(this.saldoAtual < valor){
            throw new SemSaldoException();
        }
    }
}
