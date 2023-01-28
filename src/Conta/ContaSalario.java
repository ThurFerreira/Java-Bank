package Conta;

import DataObjects.Agencia;
import DataObjects.TransacaoBancaria;
import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import Exceptions.ValorInvalidoException;

public class ContaSalario extends Conta{

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
    public void sacar(int senha, int valor, String canal) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException {
        isActive();
        confirmaSenha(senha);
        verificaSaldo(valor);

        //verificando valor a ser sacado
        if(valor > 0 && valor <= this.limiteSaque){
            super.saldoAtual -= valor;
            TransacaoBancaria novaTransacao = new TransacaoBancaria(valor, 1, canal);
            historico.add(novaTransacao);

        }else{
            throw new ValorInvalidoException();
        }
    }
}
