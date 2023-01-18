package Conta;

import Exceptions.SenhaIncorretaException;

public interface TransacoesBancarias {

    public void sacar(int senha, int valor) throws SenhaIncorretaException;
    public void depositar(int senha, int valor);

    public int consultarSaldo(int senha);

    public void efeturarPagamento(int senha, int valor, String data);
}
