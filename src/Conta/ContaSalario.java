package Conta;

import DataObjects.Agencia;
import DataObjects.TransacaoBancaria;
import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import Exceptions.ValorInvalidoException;

import java.io.Serializable;

public class ContaSalario extends Conta implements Serializable {

    private int limiteTransferencia;
    private int limiteSaque;

    public ContaSalario(int numeroConta, int senha, Agencia agencia, int limiteTransferencia, int limiteSaque) {
        super(numeroConta, senha, agencia);
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

    @Override
    public void sacar(double valor, String canal) throws SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();
        verificaSaldo(valor);

        //verificando valor a ser sacado
        if(valor > 0.0 && valor <= limiteSaque){
            super.saldoAtual -= valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 1, canal);
            historico.add(novaTransacao);
            ultimaMovimentacao = novaTransacao;

        }else if(valor < 0d){
            throw new ValorInvalidoException();
        }
    }
}
