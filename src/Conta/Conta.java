package Conta;

import Cliente.Cliente;
import DataObjects.Agencia;

import java.lang.ref.Cleaner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Conta {

    private int senha;
    private int isActive; //1 for active, 0 for deactivate
    private int numeroConta;
    private int currentBalance;
    private final String dataAberturaConta;
    private String ultimaMovimentacao;
    private Cliente[] clientes = new Cliente[1];
    private Agencia agencia;

    public Conta(int numeroConta, int senha, Cliente[] clientes, Agencia agencia){
        this.senha = senha;
        this.isActive = 1;
        this.numeroConta = numeroConta;
        this.currentBalance = 0;
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

    public int getCurrentBalance() {
        return currentBalance;
    }

    /*public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }*/

    public String getDataAberturaConta() {
        return dataAberturaConta;
    }

    /*public void setDataAberturaConta(String dataAberturaConta) {
        this.dataAberturaConta = dataAberturaConta;
    }*/

    public String getUltimaMovimentacao() {
        return ultimaMovimentacao;
    }



    public void setUltimaMovimentacao(String ultimaMovimentacao) {
        this.ultimaMovimentacao = ultimaMovimentacao;
    }

    private String getCurrentDate() {
        //getting and formatting the current account creation date
        //https://stackabuse.com/how-to-get-current-date-and-time-in-java/
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");//format patter
        Date date = new Date(System.currentTimeMillis());//System.currentTimeMillis() get the current date
        return formatter.format(date);
    }
}
