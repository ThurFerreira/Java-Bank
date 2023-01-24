package Conta;

import java.util.Date;
import DataObjects.Agencia;
import DataObjects.CurrentDate;
import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import java.io.Serializable;


public abstract class Conta implements TransacoesBancarias, Serializable {

    private int senha;
    private int isActive; //1 for active, 0 for deactivate
    private int numeroConta;
    private Double saldoAtual;
    private Date dataAberturaConta;
    private String ultimaMovimentacao;
    private Agencia agencia;

    public Conta(int numeroConta, int senha, Agencia agencia) {
        this.senha = senha;
        this.isActive = 1;
        this.numeroConta = numeroConta;
        this.saldoAtual = 0d;
        this.dataAberturaConta = CurrentDate.getCurrentDate();
    }

    public Conta(int senha){
        this.senha = senha;
        this.dataAberturaConta = CurrentDate.getCurrentDate();
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

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    /*public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }*/

    public Double getSaldoAtual() {
        return saldoAtual;
    }

    /*public void setSaldoAtual(int currentBalance) {
        this.currentBalance = currentBalance;
    }*/

    public String getDataAberturaConta() {
        return CurrentDate.showDate(this.dataAberturaConta);
    }

    public String getUltimaMovimentacao() {
        return this.ultimaMovimentacao;
    }


    public void setUltimaMovimentacao(String ultimaMovimentacao) {
        this.ultimaMovimentacao = ultimaMovimentacao;
    }

    @Override
    public void sacar(int senha, int valor) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException {
        isActive();
        confirmaSenha(senha);
        verificaSaldo(valor);

        this.saldoAtual -= valor;

    }

    @Override
    public void depositar(int senha, int valor) throws SenhaIncorretaException, ContaDesativadaException {
        isActive();
        confirmaSenha(senha);

        this.saldoAtual += valor;
    }

    @Override
    public int consultarSaldo(int senha) throws SenhaIncorretaException, ContaDesativadaException {
        isActive();
        confirmaSenha(senha);

        System.out.printf("R$ %.2f\n", this.saldoAtual);

        return 0;
    }

    @Override
    public void efeturarPagamento(int senha, int valor, String data) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException {
        isActive();
        confirmaSenha(senha);
        verificaSaldo(valor);

        this.saldoAtual -= valor;

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
