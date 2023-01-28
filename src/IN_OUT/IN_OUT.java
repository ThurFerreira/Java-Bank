package IN_OUT;

import Conta.Conta;
import DataObjects.Agencia;
import Entidades.Cliente.Cliente;
import Relacional.ClienteConta;
import Entidades.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class IN_OUT {
    public static void addClienteContaToDataBase(ClienteConta novaConta) throws IOException{

        //abrindo streams
            FileOutputStream arq = new FileOutputStream("ClienteContaDataBase.dat", false);
            //FileOutputStream arq = new FileOutputStream("ClienteContaDataBase.dat");
            ObjectOutputStream os = new ObjectOutputStream(arq);

            //escrevendo o objeto no arquivo
            os.writeObject(novaConta);

            //fechando arquivo
            os.close();
    }
    public static void addClienteToDataBase(Cliente novaConta) throws IOException{

        //abrindo streams
        FileOutputStream arq = new FileOutputStream("ClienteDataBase.dat");
        ObjectOutputStream os = new ObjectOutputStream(arq);

        //escrevendo o objeto no arquivo
        os.writeObject(novaConta);

        //fechando arquivo
        os.close();
        arq.close();
    }

    public static void loadClienteDatabase(Set<Cliente> dataBase){
        //readfrom file
        Cliente newCliente;

        try {
            FileInputStream arq = new FileInputStream("ClienteDataBase.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            while((newCliente = (Cliente) in.readObject()) != null){
                dataBase.add(newCliente);
                System.out.println("do banco" + newCliente.toString());
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

    public static void loadClienteContaDatabase(Set<ClienteConta> dataBase){
        //readfrom file
        ClienteConta novaConta;

        try {
            FileInputStream arq = new FileInputStream("ClienteContaDataBase.dat");
            ObjectInputStream in = new ObjectInputStream(arq);

            while((novaConta = (ClienteConta) in.readObject()) != null){
                dataBase.add(novaConta);
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

    public void loadAgecia(Set<Agencia> agencias){

    }

    public void atualizaAgencia(){

    }

    public void atualizaDB(){

    }
}
