package Conta;

import Cliente.Cliente;
import DataObjects.Agencia;
import DataObjects.CurrentDate;
import Exceptions.SenhaIncorretaException;
import java.io.Serializable;


public class Conta implements TransacoesBancarias, CurrentDate, Serializable {

    private int senha;
    private int isActive; //1 for active, 0 for deactivate
    private int numeroConta;
    private int saldoAtual;
    private final String dataAberturaConta;
    private String ultimaMovimentacao;
    private Agencia agencia;

    public Conta(int numeroConta, int senha, Agencia agencia) {
        this.senha = senha;
        this.isActive = 1;
        this.numeroConta = numeroConta;
        this.saldoAtual = 0;
        this.dataAberturaConta = getCurrentDate();
    }

    public Conta(int senha){
        this.senha = senha;
        this.dataAberturaConta = getCurrentDate();
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

    public int getSaldoAtual() {
        return saldoAtual;
    }

    /*public void setSaldoAtual(int currentBalance) {
        this.currentBalance = currentBalance;
    }*/

    public String getDataAberturaConta() {
        return dataAberturaConta;
    }

    /*public void setDataAberturaConta(String dataAberturaConta) {
        this.dataAberturaConta = dataAberturaConta;
    }*/

    public String getUltimaMovimentacao() {
        return this.ultimaMovimentacao;
    }


    public void setUltimaMovimentacao(String ultimaMovimentacao) {
        this.ultimaMovimentacao = ultimaMovimentacao;
    }

    @Override
    public void sacar(int senha, int valor) throws SenhaIncorretaException {
        confirmaSenha(senha);

        System.out.println("senha correta, continua o saque");
    }

    @Override
    public void depositar(int senha, int valor) throws SenhaIncorretaException {
        confirmaSenha(senha);

    }

    @Override
    public int consultarSaldo(int senha) throws SenhaIncorretaException {
        confirmaSenha(senha);

        return 0;
    }

    @Override
    public void efeturarPagamento(int senha, int valor, String data) throws SenhaIncorretaException {
        confirmaSenha(senha);

    }

    private void confirmaSenha(int senha) throws SenhaIncorretaException {
        confirmaSenha(senha);

        if (senha != this.senha)
            throw new SenhaIncorretaException();
    }
}
