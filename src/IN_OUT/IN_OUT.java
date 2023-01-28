package IN_OUT;

import Conta.Conta;
import DataObjects.Agencia;
import Entidades.Cliente.Cliente;
import Relacional.ClienteConta;
import Entidades.Pessoa;

import java.io.*;
import java.util.*;

public class IN_OUT {
    public static void addClienteContaToDataBase(ClienteConta novaConta){

        //abrindo streams
        try {
            FileOutputStream arq = new FileOutputStream("ClienteContaDataBase.dat");
            ObjectOutputStream os = new ObjectOutputStream(arq);

            //escrevendo o objeto no arquivo
            os.writeObject(novaConta);

            //fechando arquivo
            os.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addClienteToDataBase(Cliente novaConta){

        //abrindo streams
        try {
            FileOutputStream arq = new FileOutputStream("ClienteDataBase.dat");
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

    public static void loadClienteDatabase(List<Cliente> dataBase){
        //readfrom file
        Cliente newCliente;

        try {
            FileInputStream arq = new FileInputStream("ClienteDataBase.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            while((newCliente = (Cliente) in.readObject()) != null){
                dataBase.add(newCliente);
            }

            in.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Banco de dados nao encontrado");;

        } catch (IOException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void loadClienteContaDatabase(List<ClienteConta> dataBase){
        //readfrom file
        ClienteConta newConta;

        try {
            FileInputStream arq = new FileInputStream("ClienteContaDataBase.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            while((newConta = (ClienteConta) in.readObject()) != null){
                dataBase.add(newConta);
                System.out.println(newConta);
            }

            in.close();
            arq.close();

        } catch (FileNotFoundException e) {
            System.out.println("Banco de dados nao encontrado");;

        } catch (IOException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizaDB(){

    }
}
