package Conta;

import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import Exceptions.ValorInvalidoException;
import java.util.Date;

public interface TransacoesBancarias {

    public void sacar(double valor, String canal) throws SemSaldoException, ContaDesativadaException, ValorInvalidoException;
    public void depositar(double valor, String canal) throws ContaDesativadaException, ValorInvalidoException;

    public int consultarSaldo(String canal) throws ContaDesativadaException;

    void efetuarPagamento(double valor, String canal, Date dataPagamento) throws SemSaldoException, ContaDesativadaException, ValorInvalidoException;
}
