package Conta;

import DataObjects.Agencia;

public class ContaPoupanca extends Conta{

    private int rendimentoMesAtual;

    public ContaPoupanca(int numeroConta, int senha, Agencia agencia){
        super(numeroConta, senha, agencia);
        this.rendimentoMesAtual = 0;
    }

    public int getRendimentoMesAtual() {
        return rendimentoMesAtual;
    }

    public void setRendimentoMesAtual(int rendimentoMesAtual) {
        this.rendimentoMesAtual = rendimentoMesAtual;
    }
}
