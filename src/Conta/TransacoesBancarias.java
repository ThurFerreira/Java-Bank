package Conta;

import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import Exceptions.ValorInvalidoException;
import java.util.Date;

public interface TransacoesBancarias {

    public void sacar(int senha, double valor, String canal) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException;
    public void depositar(int senha, double valor, String canal) throws SenhaIncorretaException, ContaDesativadaException, ValorInvalidoException;

    public int consultarSaldo(int senha, String canal) throws SenhaIncorretaException, ContaDesativadaException;

    void efetuarPagamento(int senha, double valor, String canal, Date dataPagamento) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException;
}
