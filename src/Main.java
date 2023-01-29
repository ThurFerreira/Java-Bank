import Conta.*;
import DataObjects.CurrentDate;
import DataObjects.Endereco;
import DataObjects.TransacaoBancaria;
import Entidades.Cliente.Cliente;
import Entidades.Funcionario.Funcionario;
import Entidades.Funcionario.Gerente;
import Entidades.Pessoa;
import Exceptions.*;
import Relacional.ClienteConta;
import DataObjects.Agencia;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import IN_OUT.*;

public class Main {
    public static void main(String[] args) {
        clearPrompt();

        ArrayList<ClienteConta> clienteConta = null;
        ArrayList<Cliente> clientes = null;
        Agencia[] agencias = new Agencia[2];

        // carregando bancos de dados
        clienteConta = IN_OUT.loadArrayListClienteConta();
        clientes = IN_OUT.loadArrayListClientes();
        createAgencias(agencias);

        Scanner sc = new Scanner(System.in);
        int escolheMenu = 0;
        int escolha = 0;
        Agencia agencia = null;
        String continuar = "";

        while (escolheMenu != 4) {
            System.out.println("Bem vindo ao Sistema Bancário!");
            System.out.println("O que deseja fazer?\n");
            System.out.println(
                    "1 - Adicionar novo cliente\n" +
                            "2 - Adicionar nova conta\n" +
                            "3 - Realizar operação em conta existente\n" +
                            "4 - Sair\n"
                            //"5 - Mostrar database\n"
                            );

            escolheMenu = sc.nextByte();

            clearPrompt();

            while (escolheMenu < 1 || escolheMenu > 5) {
                System.out.println("Escolha inválida. Digite sua escolha novamente:");
                escolha = sc.nextInt();
            }

            boolean verify = false;
            sc.nextLine();
            Cliente cliente = null;

            switch (escolheMenu) {
                case 1:// cadastra novo cliente
                    try {
                        cliente = cadastraCliente(sc, verify, clientes);

                        // adiciona o novo cliente a lista
                        clientes.add(cliente);

                    } catch (ClienteJaExistenteException e) {
                        System.out.println(e.getMessage());

                    }
                    break;

                case 2:// cadastra nova conta

                    System.out.println("O cliente ja é cadastrado? \n [1] SIM [2] NAO");
                    escolha = sc.nextInt();
                    sc.nextLine();

                    if (escolha == 1) {
                        while (!verify) {
                            // cliente ja existe, buscando
                            System.out.println("Digite o CPF do cliente: ");
                            String cpf = sc.nextLine();

                            // buscando o cliente na base de dados
                            for (Cliente c : clientes) {
                                if (cpf.equals(c.getCpf())) {// achou
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

                    if (escolha == 2) {// cadastrando cliente caso ele nao exista no banco de dados

                        try {
                            cliente = cadastraCliente(sc, verify, clientes);
                            // adiciona a lista
                            clientes.add(cliente);

                        } catch (ClienteJaExistenteException e) {
                            System.out.printf(e.getMessage());
                        }
                    }

                    // cadastro da conta
                    System.out.println(
                            "Qual o tipo da conta: \n [1] Conta corrente \n [2] Conta Poupança \n [3] Conta Salário");
                    escolha = sc.nextByte();

                    switch (escolha) {// escolha do tipo da conta (Corrente, Poupança ou Salario)
                        case 1:// conta corrente

                            agencia = null;
                            verify = false;

                            System.out.println("Selecione a agencia mais proxima da sua casa: ");
                            while (!verify) {
                                int i = 0;

                                for (Agencia a : agencias) {// listando agencias para escolha
                                    System.out.println((i + 1) + " - " + a.getNome());
                                    i++;
                                }

                                escolha = sc.nextByte();

                                i = 1;
                                for (Agencia a : agencias) {// retornando a agencia escolhida e parando o laço
                                    if (i == escolha) {
                                        agencia = a;
                                        verify = true;
                                        break;
                                    }

                                    i++;
                                }

                                if (agencia == null) {
                                    System.out.printf("Agencia nao encontrada! Verifique e tente novamente. \n");
                                }
                            }

                            System.out.println("Digite a senha de 4 numeros da conta");
                            int senha = sc.nextInt();

                            System.out.println("Digite o limite do cheque especial: ");
                            int limiteCheque = sc.nextInt();

                            System.out.println("Informe o valor da Taxa de Administraçao: ");
                            int taxaAdm = sc.nextInt();

                            // gerando a conta corrente
                            ContaCorrente newContaC = new ContaCorrente(clienteConta.size() + 1, senha, agencia,
                                    limiteCheque, taxaAdm);
                            newContaC.setAgencia(agencia);
                            ClienteConta cc = new ClienteConta(cliente, newContaC);
                            // salvando no banco de dados
                            clienteConta.add(cc);

                            System.out.println("Conta cadastrada com sucesso! \nPressione enter para continuar...");
                            break;

                        case 2: // Conta poupança

                            verify = false;

                            System.out.println("Selecione a agencia mais proxima da sua casa: ");
                            while (!verify) {
                                int i = 0;

                                for (Agencia a : agencias) {// listando agencias para escolha
                                    System.out.println((i + 1) + " - " + a.getNome());
                                    i++;
                                }

                                escolha = sc.nextByte();

                                i = 1;
                                for (Agencia a : agencias) {// retornando a agencia escolhida e parando o laço
                                    if (i == escolha) {
                                        agencia = a;
                                        verify = true;
                                        break;
                                    }

                                    i++;
                                }

                                if (agencia == null) {
                                    System.out.printf("Agencia nao encontrada! Verifique e tente novamente. \n");
                                }
                            }

                            System.out.println("Digite a senha de 4 numeros da conta");
                            senha = sc.nextInt();

                            // gerando a conta poupança
                            ContaPoupanca newContaP = new ContaPoupanca(clienteConta.size() + 1, senha, agencia);
                            newContaP.setAgencia(agencia);
                            ClienteConta cp = new ClienteConta(cliente, newContaP);
                            // adicionando ao banco de dados
                            clienteConta.add(cp);

                            System.out.println("Conta cadastrada com sucesso! \nPressione enter para continuar...");

                            break;

                        case 3: // conta salario

                            verify = false;

                            System.out.println("Selecione a agencia mais proxima da sua casa: ");

                            while (!verify) {
                                int i = 0;

                                for (Agencia a : agencias) {// listando agencias para escolha
                                    System.out.println((i + 1) + " - " + a.getNome());
                                    i++;
                                }

                                escolha = sc.nextByte();

                                i = 1;
                                for (Agencia a : agencias) {// retornando a agencia escolhida e parando o laço
                                    if (i == escolha) {
                                        agencia = a;
                                        verify = true;
                                        break;
                                    }

                                    i++;
                                }

                                if (agencia == null) {
                                    System.out.printf("Agencia nao encontrada! Verifique e tente novamente. \n");
                                }
                            }

                            System.out.println("Digite a senha de 4 numeros da conta: ");
                            senha = sc.nextInt();

                            System.out.println("Informe o limite para de transferencia: ");
                            int limiteTransferencia = sc.nextInt();

                            System.out.println("Informe o limite para saques: ");
                            int limiteSaque = sc.nextInt();

                            // gerando a conta salario
                            ContaSalario newContaS = new ContaSalario(clienteConta.size() + 1, senha, agencia,
                                    limiteTransferencia, limiteSaque);
                            newContaS.setAgencia(agencia);
                            ClienteConta cs = new ClienteConta(cliente, newContaS);
                            // salvando no banco de dados
                            clienteConta.add(cs);

                            System.out.println("Conta cadastrada com sucesso! \nPressione enter para continuar...");
                            break;
                    }
                    break;// fim case 2

                case 3: // realizar operações
                    System.out.println("Insira o número da conta e a senha.");

                    Conta conta = null;
                    verify = false;
                    while (!verify) {
                        System.out.println("Número da conta: ");
                        int numeroConta = sc.nextInt();

                        for (ClienteConta c : clienteConta) {
                            if (numeroConta == c.getConta().getNumeroConta()) {// achou
                                conta = c.getConta();
                                verify = true;
                                break;
                            }
                        }

                        if (conta == null) {
                            System.out.println("A conta informada não existe! Verifique e tente novamente.");
                        }
                    }

                    System.out.println("Senha: ");
                    int senha = sc.nextInt();

                    System.out.println("Selecione a operação que deseja realizar: \n" +
                            "[1] Saque\n" +
                            "[2] Deposito\n" +
                            "[3] Consultar Saldo atual\n" +
                            "[4] Efetuar Pagamento\n");
                    escolha = sc.nextInt();

                    clearPrompt();

                    verify = false;
                    int verifyError = 2;
                    double valor = 0;
                    switch (escolha) {
                        case 1:// saque

                            while (!verify) {
                                if(verifyError == 1){
                                    System.out.println("Digite a senha novamente:");
                                    senha = sc.nextInt();
                                }else if(verifyError == 2){
                                    System.out.println("Insira o valor do saque: ");
                                    valor = sc.nextDouble();
                                }

                                String meio = escolheMeio();

                                try {
                                    conta.sacar(senha, valor, meio);
                                    verifyError = 0;

                                } catch (SenhaIncorretaException e) {
                                    System.out.println(e.getMessage());
                                    verifyError = 1;

                                } catch (SemSaldoException e) {
                                    System.out.println(e.getMessage());
                                    break;

                                } catch (ContaDesativadaException e) {
                                    System.out.println(e.getMessage());
                                    break;

                                } catch (ValorInvalidoException e) {
                                    System.out.println(e.getMessage());
                                    verifyError = 2;

                                }

                                if (verifyError == 0) {
                                    verify = true;
                                    System.out.println("Operação Efetuada com sucesso! \nPressione enter para continuar...");
                                }
                            }
                            break;

                        case 2:// deposito

                            while (!verify) {
                                if(verifyError == 1){
                                    System.out.println("Digite a senha novamente:");
                                    senha = sc.nextInt();
                                }else if(verifyError == 2){
                                    System.out.println("Insira o valor do depósito: ");
                                    valor = sc.nextDouble();
                                }

                                String meio = escolheMeio();

                                try {
                                    conta.depositar(senha, valor, meio);
                                    verifyError = 0;

                                } catch (SenhaIncorretaException e) {
                                    System.out.println(e.getMessage());
                                    verifyError = 1;

                                } catch (ContaDesativadaException e) {
                                    System.out.println(e.getMessage());
                                    break;

                                } catch (ValorInvalidoException e) {
                                    System.out.println(e.getMessage());
                                    verifyError = 2;

                                }

                                if (verifyError == 0) {
                                    verify = true;
                                    System.out.println("Operação Efetuada com sucesso! \nPressione enter para continuar...");
                                }
                            }
                            break;

                        case 3:// consultar saldo

                            String meio = escolheMeio();


                            try {
                                conta.consultarSaldo(senha, meio);

                            } catch (SenhaIncorretaException e) {
                                System.out.println(e.getMessage());
                                break;

                            } catch (ContaDesativadaException e) {
                                System.out.println(e.getMessage());
                                break;
                            }

                            System.out.printf("Saldo Atual: R$ %.2f\n", conta.getSaldoAtual());

                            System.out.println("Pressione enter para continuar...");

                            break;

                        case 4:// efetuar pagamento
                            while (!verify) {

                                if(verifyError == 1){
                                    System.out.println("Digite a senha novamente:");
                                    senha = sc.nextInt();
                                }else if(verifyError == 2){
                                    System.out.println("Insira o valor do pagamento: ");
                                    valor = sc.nextDouble();
                                }

                                System.out.println("Insira a data para efetuar o pagamento no formato dia/mes/ano: ");
                                Date date = getFormateDate();

                                Date dataPagamento = new Date();

                                meio = escolheMeio();

                                try {
                                    conta.efeturarPagamento(senha, valor, meio, dataPagamento);
                                    verifyError = 0;

                                } catch (SenhaIncorretaException e) {
                                    System.out.println(e.getMessage());
                                    verifyError = 1;

                                } catch (ContaDesativadaException e) {
                                    System.out.println(e.getMessage());
                                    break;

                                } catch (SemSaldoException e) {
                                    System.out.println(e.getMessage());
                                    if(conta.getSaldoAtual() == 0) break;
                                    else {
                                        verifyError = 2;
                                        System.out.println("Seu saldo atual é insuficiente. Tente novamente.");
                                    }

                                } catch (ValorInvalidoException e) {
                                    System.out.println(e.getMessage());
                                    verifyError = 2;

                                }

                                if (verifyError == 0) {
                                    verify = true;
                                    System.out.println("Operação realizada com sucesso! \nPressione enter para continuar...");
                                }
                            }
                            break;
                    }
                    break;// fim do case 3

                case 4:
                    IN_OUT.saveArrayListClienteConta(clienteConta);
                    IN_OUT.saveArrayListClientes(clientes);
                    break;
                
                    /*
                case 5:
                    System.out.println("ClienteConta:");
                    for (ClienteConta cc : clienteConta) {
                        if (cc.getConta() instanceof ContaCorrente) {
                            System.out.println("Dono : " + cc.getCliente());
                            System.out.println("Conta Corrente: " + ((ContaCorrente) cc.getConta()) + "\n---");

                        } else if (cc.getConta() instanceof ContaPoupanca) {
                            System.out.println("Dono : " + cc.getCliente());
                            System.out.println("Conta Poupança: " + ((ContaPoupanca) cc.getConta()));
                            System.out.println("rendimento mes atual: "
                                    + ((ContaPoupanca) cc.getConta()).getRendimentoMesAtual() + "\n---");

                        } else {
                            System.out.println("Dono : " + cc.getCliente());
                            System.out.println("Conta Salario: " + ((ContaSalario) cc.getConta()));
                            System.out.println(
                                    "limite de saque: " + ((ContaSalario) cc.getConta()).getLimiteSaque() + "\n---");
                        }
                    }
                    System.out.println("\nClientes: \n" + clientes);
                    break;
                    */
            }

            continuar = sc.nextLine();
            continuar = sc.nextLine();
            clearPrompt();
        }
    }

    public static Cliente cadastraCliente(Scanner sc, boolean verify, List<Cliente> clientes)
            throws ClienteJaExistenteException {
        String cpf = "";
        while (!verify) {
            System.out.println("Insira o CPF do cliente : ");
            cpf = sc.nextLine();
            verify = Pessoa.isValidCPF(cpf);

            // verificando se o cliente ja existe na base de dados
            for (Cliente c : clientes) {
                if (cpf.equals(c.getCpf())) {
                    throw new ClienteJaExistenteException();
                }
            }
        }

        System.out.println("Insira o Nome do cliente: ");
        String nome = sc.nextLine();

        System.out.println("Insira o endereço do cliente no formato Rua/Bairro/Cidade: ");
        String enderecoString = sc.nextLine();
        String[] endereco = new String[3];
        endereco = enderecoString.split("/");

        System.out.println("Insira o Estado Civil do cliente: ");
        String estadoCivil = sc.nextLine();

        System.out.println("Insira a Escolaridade do cliente: ");
        String escolaridade = sc.nextLine();

        System.out.println("Insira a data de nascimento do cliente no formato dia/mes/ano: ");
        Date date = getFormateDate();

        Cliente newCliente = new Cliente(cpf, nome, new Endereco(endereco[0], endereco[1], endereco[2]), estadoCivil,
                escolaridade, date);

        System.out.println("Cliente cadastrado com sucesso \nPressione enter para continuar...");
        return newCliente;
    }

    public static void createAgencias(Agencia[] agencias) {

        Agencia agencia1 = new Agencia(
                123,
                "Agencia Santa Monica",
                new Endereco("Uberlandia", "Minas Gerais", "Santa Monica"),
                new Gerente("040.058.140-00", "Josimar", "19.305.188-6", new Date(1999, 12, 04),
                        new Endereco("Uberlandia", "Minas Gerais", "Santa Monica"), "10564786981", 'M', "casado",
                        19.000, CurrentDate.getCurrentDate(), 1));

        Agencia agencia2 = new Agencia(
                321,
                "Agencia Capão Redondo",
                new Endereco("Sao Paulo", "São Paulo", "Capão Redondo"),
                new Gerente("363.995.940-06", "Francisca", "29.083.447-8", new Date(1980, 2, 26),
                        new Endereco("Sao Paulo", "Sao Paulo", "Capão Redondo"), "50719719736", 'F', "solteira", 19.000,
                        CurrentDate.getCurrentDate(), 1));

        agencias[0] = ((((((((((((((((agencia1))))))))))))))));
        agencias[1] = ((((((((((((((((agencia2))))))))))))))));
    }

    public void loadContasAgencia(List<Conta> contas, Agencia[] agencias) {
        for (Conta c : contas) {

            if (c.getAgencia().getNumero() == 123) {
                agencias[0].setContaToAgencia(c);

            } else if (c.getAgencia().getNumero() == 321) {
                agencias[1].setContaToAgencia(c);

            }
        }
    }

    public static String escolheMeio() {
        Scanner sc = new Scanner(System.in);

        System.out.println(
                "Insira por onde o dinheiro será depositado:\n [1] Caixa Físico\n [2] Caixa Eletrônico\n [3] Internet Banking");
        int option = sc.nextInt();
        String meio = null;

        if (option == 1) {
            meio = "Caixa Físico";

        } else if (option == 2) {
            meio = "Caixa Eletrônico";

        } else if (option == 3) {
            meio = "Internet Banking";

        }

        return meio;
    }

    public static Date getFormateDate() {
        Scanner sc = new Scanner(System.in);

        String stringData = sc.nextLine();
        String[] data = new String[3];
        data = stringData.split("/");
        LocalDate dataNacimento = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]),
                Integer.parseInt(data[0]));
        Date date = Date.from(dataNacimento.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return date;
    }

    public static void clearPrompt(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}