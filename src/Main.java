import Conta.*;
import Exceptions.SenhaIncorretaException;
import Funcionario.Funcionario;
import Cliente.Cliente;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Conta conta = new Conta(123);

        try {
            conta.sacar(1233, 4);

        } catch (SenhaIncorretaException e) {
            System.out.println(e.getMessage());
        }
    }
}