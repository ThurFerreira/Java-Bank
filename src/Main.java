import Conta.*;
import DataObjects.Endereco;
import Entidades.Cliente.Cliente;
import Entidades.Funcionario.Gerente;
import Entidades.Pessoa;
import Exceptions.ClienteJaExistenteException;
import Exceptions.SenhaIncorretaException;
import Relacional.ClienteConta;
import DataObjects.Agencia;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import IN_OUT.*;

public class Main {
    public static void main(String[] args) {
        List<ClienteConta> clienteConta = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Agencia> agencias = new ArrayList<>();

        IN_OUT.loadClienteContaDatabase(clienteConta);
        IN_OUT.loadClienteDatabase(clientes);

        System.out.println(clientes.size());

        Scanner sc = new Scanner(System.in);
        int escolha = 0;
        Agencia agencia = null;

        System.out.println("Bem vindo ao Sistema Bancário!");
        while (escolha != 4) {
            System.out.println("O que deseja fazer?\n");
            System.out.println(
                    "1 - Adicionar novo cliente\n" +
                            "2 - Adicionar nova conta\n" +
                            "3 - Realizar operação em conta existente\n" +
                            "4 - Sair\n");

            escolha = sc.nextByte();

            while (escolha < 1 || escolha > 4) {
                System.out.println("Escolha inválida. Digite sua escolha novamente:");
                escolha = sc.nextInt();
            }

            boolean verify = false;
            sc.nextLine();
            Cliente cliente = null;

            switch (escolha) {
                case 1://cadastra novo cliente
                    try {
                        cliente = cadastraCliente(sc, verify, clientes);

                        //adiciona o novo cliente no hashset
                        clientes.add(cliente);
                        System.out.println("2" + cliente.toString());
                        IN_OUT.addClienteToDataBase(cliente);//salva no banco de dados

                    } catch (ClienteJaExistenteException e) {
                        throw new RuntimeException(e);

                    } catch (IOException e) {
                        throw new RuntimeException(e);

                    }

                    break;

                case 2://cadastra nova conta

                        System.out.println("O cliente ja é cadastrado? \n [1] SIM [2] NAO");
                        escolha = sc.nextInt();

                    while (!verify){
                        if (escolha == 1) {//cliente ja existe, buscando
                            System.out.println("Digite o CPF do cliente: ");
                            String cpf = sc.next();

                            //buscando o cliente na base de dados
                            for (Cliente c : clientes) {
                                if (cpf.equals(c.getCpf())) {//achou
                                    cliente = c;
                                    verify = true;
                                    break;
                                }
                            }

                            if (cliente == null) {
                                System.out.println("Cliente não encontrado ou cpf inválido. Tente novamente.");
                            }
                        }
                    }

                    if(escolha == 2){//cadastrando cliente caso ele nao exista no banco de dados
                        try {
                            cliente = cadastraCliente(sc, verify, clientes);

                        } catch (ClienteJaExistenteException e) {

                            System.out.printf(e.getMessage());
                        }

                        //adiciona no hash o cliente novo
                        clientes.add(cliente);
                    }

                    //cadastro da conta
                    System.out.println("Qual o tipo da conta: \n [1] Conta corrente \n [2] Conta Salario \n [3] Conta Poupança");
                    escolha = sc.nextByte();

                    switch (escolha){//escolha do tipo da conta (Corrente, Poupança ou Salario)
                        case 1://conta corrente
                            agencia = null;
                            System.out.println("Digite a senha da conta: ");
                            System.out.println("Selecione a agencia mais proxima da sua casa: ");


                            while (!verify){
                                int i = 0;

                                for (Agencia a : agencias){//listando agencias para escolha

                                    System.out.println((i+1) + " - " + a.getNome());
                                    i++;
                                }

                                escolha = sc.nextByte();

                                i = 0;
                                for (Agencia a: agencias) {//retornando a agencia escolhida e parando o laço
                                    if(i == escolha-1){
                                        agencia = a;
                                        verify = true;
                                        break;
                                    }
                                }

                                if(agencia == null){
                                    System.out.printf("Agencia nao encontrada! Verifique e tente novamente.");
                                }
                            }

                            System.out.println("Digite a senha de 4 numeros da conta");
                            int senha = sc.nextByte();

                            System.out.println("Digite o limite do cheque especial: ");
                            int limiteCheque = sc.nextInt();

                            System.out.println("Informe o valor da Taxa de Administraçao: ");
                            int taxaAdm = sc.nextInt();

                            ContaCorrente newContaC = new ContaCorrente(clienteConta.size()+1, senha, agencia, limiteCheque, taxaAdm);

                            ClienteConta cc = new ClienteConta(cliente, newContaC);
                            clienteConta.add(cc);
                            break;

                        case 2: //Conta poupança

                            System.out.println("Digite a senha da conta: ");
                            System.out.println("Selecione a agencia mais proxima da sua casa: ");


                            while (!verify){
                                int i = 0;

                                for (Agencia a : agencias){//listando agencias para escolha

                                    System.out.println((i+1) + " - " + a.getNome());
                                    i++;
                                }

                                escolha = sc.nextByte();

                                i = 0;
                                for (Agencia a: agencias) {//retornando a agencia escolhida e parando o laço
                                    if(i == escolha-1){
                                        agencia = a;
                                        verify = true;
                                        break;
                                    }
                                }

                                if(agencia == null){
                                    System.out.printf("Agencia nao encontrada! Verifique e tente novamente.");
                                }
                            }

                            System.out.println("Digite a senha de 4 numeros da conta");
                            senha = sc.nextByte();

                             ContaPoupanca newContaP = new ContaPoupanca(clienteConta.size()+1, senha, agencia);

                            ClienteConta cp = new ClienteConta(cliente, newContaP);
                            clienteConta.add(cp);
                            break;

                        case 3: //conta salario

                            System.out.println("Digite a senha da conta: ");
                            System.out.println("Selecione a agencia mais proxima da sua casa: ");


                            while (!verify){
                                int i = 0;

                                for (Agencia a : agencias){//listando agencias para escolha

                                    System.out.println((i+1) + " - " + a.getNome());
                                    i++;
                                }

                                escolha = sc.nextByte();

                                i = 0;
                                for (Agencia a: agencias) {//retornando a agencia escolhida e parando o laço
                                    if(i == escolha-1){
                                        agencia = a;
                                        verify = true;
                                        break;
                                    }
                                }

                                if(agencia == null){
                                    System.out.printf("Agencia nao encontrada! Verifique e tente novamente.");
                                }
                            }

                            System.out.println("Digite a senha de 4 numeros da conta: ");
                            senha = sc.nextByte();

                            System.out.println("Informe o limite para de transferencia: ");
                            int limiteTransferencia = sc.nextByte();

                            System.out.println("Informe o limite para saques: ");
                            int limiteSaque = sc.nextByte();

                            ContaSalario newContaS = new ContaSalario(clienteConta.size()+1, senha, agencia, limiteTransferencia, limiteSaque);
                            ClienteConta cs = new ClienteConta(cliente, newContaS);
                            clienteConta.add(cs);
                            break;
                    }
                    break;//fim case 2

                case 3: //realizar operações

                    break;
            }
        }
    }

    public static Cliente cadastraCliente(Scanner sc, boolean verify, Set<Cliente> clientes) throws ClienteJaExistenteException {
        String cpf = "";
        while (!verify) {
            System.out.println("Insira o CPF do cliente : ");
            cpf = sc.nextLine();
            verify = Pessoa.isValidCPF(cpf);

            //verificando se o cliente ja existe na base de dados
            for (Cliente c: clientes) {
                if(cpf.equals(c.getCpf())){
                    System.out.println("O cliente ja existe!");
                    throw new ClienteJaExistenteException();
                }
            }
        }

        System.out.println("Insira o Nome do cliente: ");
        String nome = sc.next();

        System.out.println("Insira o endereço do cliente no formato Rua/Bairro/Cidade: ");
        String enderecoString = sc.next();
        String[] endereco = new String[3];
        endereco = enderecoString.split("/");

        System.out.println("Insira o Estado Civil do cliente: ");
        String estadoCivil = sc.next();

        System.out.println("Insira a Escolaridade do cliente: ");
        String escolaridade = sc.next();

        System.out.println("Insira a data de nascimento do cliente no formato dia/mes/ano: ");
        String stringData = sc.next();
        String[] data = new String[3];
        data = stringData.split("/");
        LocalDate dataNacimento = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
        Date date = Date.from(dataNacimento.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Cliente newCliente = new Cliente(cpf, nome, new Endereco(endereco[0], endereco[1], endereco[2]), estadoCivil, escolaridade, date);

        System.out.println("Cliente cadastrado com sucesso");
        return newCliente;
    }
}