package Conta;

import DataObjects.Agencia;

public class ContaCorrente extends Conta{

    private int limiteDoChequeEspecial;
    private int valorTaxaAdm;

    ContaCorrente(int numeroConta, int senha, Agencia agencia, int limiteDoChequeEspecial, int valorTaxaAdm){
        super(numeroConta, senha, agencia);
        this.limiteDoChequeEspecial = limiteDoChequeEspecial;
        this.valorTaxaAdm = valorTaxaAdm;
    }

    public int getLimiteDoChequeEspecial() {
        return limiteDoChequeEspecial;
    }

    public void setLimiteDoChequeEspecial(int limiteDoChequeEspecial) {
        this.limiteDoChequeEspecial = limiteDoChequeEspecial;
    }

    public int getValorTaxaAdm() {
        return valorTaxaAdm;
    }

    public void setValorTaxaAdm(int valorTaxaAdm) {
        this.valorTaxaAdm = valorTaxaAdm;
    }
}
