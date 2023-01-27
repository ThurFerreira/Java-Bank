package Conta;

import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;
import Exceptions.ValorInvalidoException;

public interface TransacoesBancarias {

    public void sacar(int senha, int valor, String canal) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException;
    public void depositar(int senha, int valor, String canal) throws SenhaIncorretaException, ContaDesativadaException, ValorInvalidoException;

    public int consultarSaldo(int senha, String canal) throws SenhaIncorretaException, ContaDesativadaException;

    void efeturarPagamento(int senha, int valor, String canal) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException, ValorInvalidoException;
}
