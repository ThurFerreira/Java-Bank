package Conta;

import DataObjects.Agencia;
import DataObjects.Cartao.Cartao;
import DataObjects.Cartao.CartaoCredito;
import DataObjects.TransacaoBancaria;
import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.ValorInvalidoException;
import com.sun.management.UnixOperatingSystemMXBean;

import java.io.Serializable;

public class ContaCorrente extends Conta implements Serializable{

    private int limiteDoChequeEspecial;
    private int valorTaxaAdm;
    private Cartao cartaoCredito;


    public ContaCorrente(int numeroConta, int senha, Agencia agencia, int limiteDoChequeEspecial, int valorTaxaAdm){
        super(numeroConta, senha, agencia);
        this.limiteDoChequeEspecial = limiteDoChequeEspecial;
        this.valorTaxaAdm = valorTaxaAdm;
        cartaoCredito = new CartaoCredito("Basic", numeroConta, 500d, senha);
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

    @Override
    public void sacar(double valor, String meio) throws SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();

        if(valor < 0.0){
            throw new ValorInvalidoException();
        }

        if(saldoAtual + limiteDoChequeEspecial >= valor){
            saldoAtual -= valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 1, meio);
            historico.add(novaTransacao);
            ultimaMovimentacao = novaTransacao;

        }else{
            throw new SemSaldoException();
        }
    }
}
