package Conta;

import java.text.SimpleDateFormat;
import java.util.*;

import DataObjects.Agencia;
import DataObjects.CurrentDate;
import DataObjects.TransacaoBancaria;
import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import Exceptions.ValorInvalidoException;

import javax.swing.*;
import java.io.Serializable;


public abstract class Conta implements TransacoesBancarias, Serializable {

    protected int senha;
    protected int isActive; //1 for active, 0 for deactivate
    protected int numeroConta;
    protected double saldoAtual;
    protected Date dataAberturaConta;
    protected TransacaoBancaria ultimaMovimentacao;
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
        return ultimaMovimentacao.toString();
    }

    public Agencia getAgencia() {
        return agencia;
    }

    @Override
    public void sacar(double valor, String meio) throws SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();
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
    public void depositar(double valor, String meio) throws ContaDesativadaException, ValorInvalidoException{
        isActive();

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
    public int consultarSaldo(String meio) throws ContaDesativadaException {
        isActive();

        System.out.printf("R$ %.2f\n", this.saldoAtual);
        TransacaoBancaria novaTransacao = new TransacaoBancaria(0, 3, meio);
        historico.add(novaTransacao);

        return 0;
    }

    @Override
    public void efetuarPagamento(double valor, String meio, Date dataPagamento) throws SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();
        verificaSaldo(valor);

        if(valor > 0d){
            this.saldoAtual -= valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 4, meio, dataPagamento);
            historico.add(novaTransacao);

        }else{
            throw new ValorInvalidoException();
        }

    }

    public void confirmaSenha(int senha) throws SenhaIncorretaException {
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

    public void mostraDados() {
        String estadoDaConta;

        if (this.isActive == 1) {
            estadoDaConta = "Ativa";
        }else{
            estadoDaConta = "Inativa";
        }

       JOptionPane.showMessageDialog(null,
                "\n Numero da Conta: " + numeroConta +
                "\n Estado da Conta: " + estadoDaConta +
                "\n Saldo Atual: " + saldoAtual +
                "\n Data de abertura da Conta: " + CurrentDate.showDate(dataAberturaConta) +
                "\n Ultima Movimentação: '" + ultimaMovimentacao + '\'' +
                "\n Agencia: " + agencia.toString()
       );
    }

    public void mostraHistorico(){
        int  i = 1;
        if(historico.size() == 0){
            System.out.println("Histórico de transação vazio!");
        }else{
            for (TransacaoBancaria t: historico) {
                System.out.println(i + " - " + t.toString() + "\n");
                i++;
            }
        }
    }
}
