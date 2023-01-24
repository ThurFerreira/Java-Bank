package IN_OUT;

import Conta.Conta;
import Relacional.ClienteConta;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IN_OUT {
    public static void loadDatabase(Set<?> dataBase){
        //read from file and save on a HashSet

        //dataBase.add();

    }

    public void addToDataBase(ClienteConta newAccount, Set<ClienteConta> dataBase){
        //add a new account to dataBase
        dataBase.add(newAccount);
    }

    public void saveInFile(Set<ClienteConta> dataBase){
        //save all dataStruct in file database
        //at end of execution

    }
}
