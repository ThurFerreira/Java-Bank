package IN_OUT;

import Conta.Conta;
import DataObjects.Agencia;
import Entidades.Cliente.Cliente;
import Relacional.ClienteConta;
import Entidades.Pessoa;

import java.io.*;
import java.util.*;

public class IN_OUT {
    /*
    public static void addClienteContaToDataBase(ClienteConta novaConta) {

        //abrindo streams
        try {
            FileOutputStream arq = new FileOutputStream("ClienteContaDataBase.dat", true);
            ObjectOutputStream os = new ObjectOutputStream(arq);

            //escrevendo o objeto no arquivo
            os.writeObject(novaConta);

            //fechando arquivo
            os.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addClienteToDataBase(Cliente novaConta) {

        //abrindo streams
        try {
            FileOutputStream arq = new FileOutputStream("ClienteDataBase.dat", true);
            ObjectOutputStream os = new ObjectOutputStream(arq);

            //escrevendo o objeto no arquivo
            os.writeObject(novaConta);

            //fechando arquivo
            os.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadClienteDatabase(List<Cliente> dataBase) {
        //readfrom file
        Cliente newCliente;

        try {
            FileInputStream arq = new FileInputStream("ClienteDataBase.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            while ((newCliente = (Cliente) in.readObject()) != null) {
                dataBase.add(newCliente);
                //System.out.println(newCliente.toString());
            }

            in.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Banco de dados nao encontrado");
            ;

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void loadClienteContaDatabase(List<ClienteConta> dataBase) {
        //readfrom file
        ClienteConta newConta;

        try {
            FileInputStream arq = new FileInputStream("ClienteContaDataBase.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            while ((newConta = (ClienteConta) in.readObject()) != null) {
                dataBase.add(newConta);
            }

            in.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Banco de dados nao encontrado");
            ;

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

     */

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
}
