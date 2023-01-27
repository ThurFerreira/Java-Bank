package Relacional;

import Entidades.Cliente.Cliente;
import Conta.Conta;

public class ClienteConta {
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
}
