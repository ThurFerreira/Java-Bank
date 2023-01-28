package Relacional;

import Entidades.Cliente.Cliente;
import Conta.Conta;

import java.io.Serializable;

public class ClienteConta implements Serializable {
    private Cliente cliente;
    private Conta conta;

    public ClienteConta(Cliente cliente, Conta conta){
        this.cliente = cliente;
        this.conta = conta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Conta getConta() {
        return conta;
    }

    @Override
    public String toString() {
        return "ClienteConta{" +
                "cliente=" + cliente.toString() +
                ", conta=" + conta.toString() +
                '}';
    }
}
