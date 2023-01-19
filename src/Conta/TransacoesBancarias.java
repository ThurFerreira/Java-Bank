package Conta;

import Exceptions.SenhaIncorretaException;

import java.rmi.server.ServerNotActiveException;

public interface TransacoesBancarias {

    public void sacar(int senha, int valor) throws SenhaIncorretaException;
    public void depositar(int senha, int valor) throws SenhaIncorretaException;

    public int consultarSaldo(int senha) throws SenhaIncorretaException;

    public void efeturarPagamento(int senha, int valor, String data) throws SenhaIncorretaException;

    }
