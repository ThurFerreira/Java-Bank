package Conta;

public class ContaSalario extends Conta{

    private int limiteTransferencia;
    private int limiteSaque;

    public ContaSalario(int numeroConta, int senha, int limiteTransferencia, int limiteSaque) {
        super(numeroConta, senha);
        this.limiteTransferencia = limiteTransferencia;
        this.limiteSaque = limiteSaque;
    }

    public int getLimiteTransferencia() {
        return limiteTransferencia;
    }

    public void setLimiteTransferencia(int limiteTransferencia) {
        this.limiteTransferencia = limiteTransferencia;
    }

    public int getLimiteSaque() {
        return limiteSaque;
    }

    public void setLimiteSaque(int limiteSaque) {
        this.limiteSaque = limiteSaque;
    }
}
