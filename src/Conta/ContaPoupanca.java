package Conta;

public class ContaPoupanca extends Conta{

    private int rendimentoMesAtual;

    public ContaPoupanca(int numeroConta, int senha){
        super(numeroConta, senha);
        this.rendimentoMesAtual = 0;
    }

    public int getRendimentoMesAtual() {
        return rendimentoMesAtual;
    }

    public void setRendimentoMesAtual(int rendimentoMesAtual) {
        this.rendimentoMesAtual = rendimentoMesAtual;
    }
}
