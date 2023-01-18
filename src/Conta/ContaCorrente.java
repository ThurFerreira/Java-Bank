package Conta;

public class ContaCorrente extends Conta{

    private int limiteDoChequeEspecial;
    private int valorTaxaAdm;

    ContaCorrente(int numeroConta, int senha, int limiteDoChequeEspecial, int valorTaxaAdm){
        super(numeroConta, senha);
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
