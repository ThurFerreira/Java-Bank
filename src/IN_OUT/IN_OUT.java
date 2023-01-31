package IN_OUT;

import Conta.Conta;
import DataObjects.Agencia;
import Entidades.Cliente.Cliente;
import Entidades.Funcionario.*;
import Relacional.ClienteConta;
import Entidades.Pessoa;

import java.io.*;
import java.util.*;

public class IN_OUT {

    public static void saveArrayListClientes(ArrayList<Cliente> clientes) {
        try {
            FileOutputStream arq = new FileOutputStream("ClienteDataBase.ser");
            ObjectOutputStream os = new ObjectOutputStream(arq);

            //escrevendo o objeto no arquivo
            os.writeObject(clientes);

            //fechando arquivo
            os.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Cliente> loadArrayListClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            FileInputStream arq = new FileInputStream("ClienteDataBase.ser");
            ObjectInputStream in = new ObjectInputStream(arq);

            clientes = (ArrayList<Cliente>) in.readObject();

            in.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Banco de dados nao encontrado");
            ;

        } catch (IOException e) {
            System.out.println("IO EXC load arraylist cliente");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    public static void saveArrayListClienteConta(ArrayList<ClienteConta> clienteConta) {
        try {
            FileOutputStream arq = new FileOutputStream("ClienteContaDataBase.ser");
            ObjectOutputStream os = new ObjectOutputStream(arq);

            //escrevendo o objeto no arquivo
            os.writeObject(clienteConta);

            //fechando arquivo
            os.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ClienteConta> loadArrayListClienteConta() {
        ArrayList<ClienteConta> clienteConta = new ArrayList<>();

        try {
            FileInputStream arq = new FileInputStream("ClienteContaDataBase.ser");
            ObjectInputStream in = new ObjectInputStream(arq);

            clienteConta = (ArrayList<ClienteConta>) in.readObject();

            in.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Banco de dados nao encontrado");

        } catch (IOException e) {
            System.out.println("IO EXC load arraylist clienteConta");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return clienteConta;
    }

    public static void saveArrayListFuncionarios(ArrayList<Funcionario> funcionarios) {
        try {
            FileOutputStream arq = new FileOutputStream("FuncionariosDataBase.ser");
            ObjectOutputStream os = new ObjectOutputStream(arq);

            //escrevendo o objeto no arquivo
            os.writeObject(funcionarios);

            //fechando arquivo
            os.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Funcionario> loadArrayListFuncionarios() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {
            FileInputStream arq = new FileInputStream("FuncionariosDataBase.ser");
            ObjectInputStream in = new ObjectInputStream(arq);

            funcionarios = (ArrayList<Funcionario>) in.readObject();

            in.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Banco de dados nao encontrado");
            ;

        } catch (IOException e) {
            System.out.println("IO EXC load arraylist cliente");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return funcionarios;
    }
}
