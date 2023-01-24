import Conta.Conta;
import Entidades.Cliente.Cliente;
import Exceptions.SenhaIncorretaException;
import Relacional.ClienteConta;
import java.util.HashSet;
import java.util.Set;
import IN_OUT.*;

public class Main {
    public static void main(String[] args) {
        Set<ClienteConta> dataBase = new HashSet<>();
        Set<Cliente> clientes = new HashSet<>();
        IN_OUT.loadDatabase(dataBase);
        IN_OUT.loadDatabase(clientes);

    }
}