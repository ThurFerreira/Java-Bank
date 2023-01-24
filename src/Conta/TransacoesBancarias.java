package Conta;

import Exceptions.ContaDesativadaException;
import Exceptions.SemSaldoException;
import Exceptions.SenhaIncorretaException;

public interface TransacoesBancarias {

    public void sacar(int senha, int valor) throws SenhaIncorretaException, SemSaldoException, ContaDesativadaException;
    public void depositar(int senha, int valor) throws SenhaIncorretaException, ContaDesativadaException;

    public int consultarSaldo(int senha) throws SenhaIncorretaException, ContaDesativadaException;

    public void efeturarPagamento(int senha, int valor, String data) throws SenhaIncorretaException, SemSaldoException,ContaDesativadaException;

    }
