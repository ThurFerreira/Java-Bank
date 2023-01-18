import Conta.Conta;
import Funcionario.Funcionario;
import Cliente.Cliente;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Conta conta = new Conta(123, 123);
        System.out.println(conta.getDataAberturaConta());

        new display().displayGUI();
    }
}