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

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import IN_OUT.*;

import javax.swing.*;

public class Main {
    static ArrayList<ClienteConta> clienteConta = null;
    static ArrayList<Cliente> clientes = null;
    static ArrayList<Funcionario> funcionarios = null;
    static Agencia[] agencias = new Agencia[2];

    public static void main(String[] args) {
       initialize();

    }

    // ************************* GERENCIAMENTO DE BANCO DE DADOS ************************* //

    public static void loadDataBases(){
        clienteConta = IN_OUT.loadArrayListClienteConta();
        clientes = IN_OUT.loadArrayListClientes();
        createAgencias(agencias);
        funcionarios = IN_OUT.loadArrayListFuncionarios();
    }

    public static void saveDataBases(){
        IN_OUT.saveArrayListClienteConta(clienteConta);
        IN_OUT.saveArrayListClientes(clientes);
        IN_OUT.saveArrayListFuncionarios(funcionarios);

    }

   // ************************* MENUS ************************* //
    public static void initialize(){
        Scanner sc = new Scanner(System.in);
        int escolheMenu = -1, escolha = 0;
        String continuar = "";
        Conta conta = null;

        // carregando bancos de dados
        loadDataBases();

        while (escolheMenu != 0) {

            escolheMenu = home();

            boolean verify = false;
            Cliente cliente;

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
                    cadastraConta();
                    break;

                case 3: // realizar opera????es
                    escolha = menuOperacoes();
                    conta = confirmaSenhaEConta();
                    clearPrompt();

                    int verifyError = -1;
                    double valor = 0;

                    switch (escolha) {
                        case 1:// saque
                            efetuaSaque(conta);
                            break;

                        case 2:// deposito
                            efetuaDeposito(conta);
                            break;

                        case 3:// consultar saldo

                            consultarSaldo(conta);
                            break;


                        case 4:// efetuar pagamento
                            efetuaPagamento(conta);
                            break;

                        case 5:
                            conta.mostraHistorico();

                            break;
                    }
                    break;// fim do case 3 (opera????es)

                case 0://sair e salvar
                    saveDataBases();

                    System.out.println("< Informa????es salvas com sucesso! >");
                    System.out.println("< Pressione enter para continuar... >");
                    break;

                case 4://mostrar dados
                    conta = confirmaSenhaEConta();

                    if(conta == null){
                        System.out.println("A conta informada n??o existe! Verifique e tente novamente.");
                    }else{
                        conta.mostraDados();
                    }

                    System.out.println("< Pressione enter para continuar...>");
                    break;

                case 6:
                    System.out.println("ClienteConta:");
                    for (ClienteConta cc : clienteConta) {
                        if (cc.getConta() instanceof ContaCorrente) {
                            System.out.println("Dono : " + cc.getCliente());
                            System.out.println("Conta Corrente: " + ((ContaCorrente) cc.getConta()));
                            System.out.println("Hist??rico da Conta: ");
                            //for (TransacaoBancaria t : cc.getConta().getHistorico()) {
                            //  System.out.println("    " + t.toString());
                            //}

                        } else if (cc.getConta() instanceof ContaPoupanca) {
                            System.out.println("Dono : " + cc.getCliente());
                            System.out.println("Conta Poupan??a: " + ((ContaPoupanca) cc.getConta()));
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

                case 5:
                    System.out.println("\t |-- Funcionarios Do Banco |--");
                    for (Funcionario f: funcionarios) {
                        System.out.println("Nome: " + f.getNome() + " | Cargo: " + f.getCargo());
                    }

                    break;

            }

            continuar = sc.nextLine();
            clearPrompt();
        }
    }

    public static int home(){
        Scanner sc = new Scanner(System.in);

        clearPrompt();
        System.out.println("Bem vindo ao Sistema Banc??rio!");
        System.out.println("O que deseja fazer?\n");
        System.out.println(
                "1 - Adicionar novo cliente\n" +
                        "2 - Adicionar nova conta.\n" +
                        "3 - Realizar opera????o em conta existente.\n" +
                        "4 - Mostrar Dados da conta.\n" +
                        "5 - Exibir funcionarios. \n" +
                        "0 - Salvar e Sair.\n"
        );

        int escolha = sc.nextByte();

        while (escolha < 0 || escolha > 7) {
            System.out.println("\n Escolha inv??lida. Digite sua escolha novamente:");
            escolha = sc.nextInt();
        }

        return escolha;
    }

    public static int menuOperacoes(){
        Scanner sc = new Scanner(System.in);

        clearPrompt();
        System.out.println("Selecione a opera????o que deseja realizar: \n" +
                "[1] Saque\n" +
                "[2] Deposito\n" +
                "[3] Consultar Saldo atual\n" +
                "[4] Efetuar Pagamento\n" +
                "[5] Extrato\n");

        int escolha = sc.nextInt();

        while (escolha < 1 || escolha > 6) {
            System.out.println("\nEscolha inv??lida. Digite sua escolha novamente:");
            escolha = sc.nextInt();
        }

        return escolha;
    }

    // ************************* FUN????ES DE CADASTRO ************************* //

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

        System.out.println("Insira o endere??o do cliente no formato Rua/Bairro/Cidade/Estado: ");
        String enderecoString = sc.nextLine();
        String[] endereco = new String[4];
        endereco = enderecoString.split("/");

        System.out.println("Insira o Estado Civil do cliente: ");
        String estadoCivil = sc.nextLine();

        System.out.println("Insira a Escolaridade do cliente: ");
        String escolaridade = sc.nextLine();

        System.out.println("Insira a data de nascimento do cliente no formato dia/mes/ano: ");
        Date date = getFormateDate();

        Cliente newCliente = new Cliente(cpf, nome, new Endereco(endereco[0], endereco[1], endereco[2], endereco[3]), estadoCivil,
                escolaridade, date);

        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        System.out.println("Cliente cadastrado com sucesso \nPressione enter para continuar...");
        return newCliente;
    }

    public static void cadastraConta(){
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        Agencia agencia = null;
        boolean verify = false;
        int senha;

        //System.out.println("O cliente ja ?? cadastrado? \n [1] SIM [2] NAO");
        //int escolha = sc.nextInt();
        int escolha = JOptionPane.showConfirmDialog(null, "J?? ?? nosso cliente?");//0 = sim

        if (escolha == 0) {
            while (!verify) {
                // cliente ja existe, buscando
                System.out.println("\nDigite o CPF do cliente: ");
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
                    JOptionPane.showMessageDialog(null, "Cliente n??o encontrado ou cpf inv??lido. Tente novamente.");
                    System.out.println("\nCliente n??o encontrado ou cpf inv??lido. Tente novamente.");
                }
            }
        }else if (escolha == 1) {// cadastrando cliente caso ele nao exista no banco de dados

            try {
                cliente = cadastraCliente(sc, verify, clientes);
                // adiciona a lista
                clientes.add(cliente);

            } catch (ClienteJaExistenteException e) {
                System.out.printf(e.getMessage());
            }
        }else{
            menuOperacoes();
        }

        // cadastro da conta
        //System.out.println("\nQual o tipo da conta: \n [1] Conta corrente \n [2] Conta Poupan??a \n [3] Conta Sal??rio \n \n [0] Voltar ao Menu\n");
        //escolha = sc.nextByte();

        Object[] options = { "Conta corrente", "Conta Poupan??a", "Conta Sal??rio", "Voltar ao Menu"};
        escolha = JOptionPane.showOptionDialog(null, "Qual o tipo da conta:", "Selecione o tipo da conta:  ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        switch (escolha) {// escolha do tipo da conta (Corrente, Poupan??a ou Salario)
            case 0:// conta corrente

                agencia = escolheAgencia();

                System.out.println("\nDigite a senha de 4 numeros da conta");
                senha = sc.nextInt();

                System.out.println("\nDigite o limite do cheque especial: ");
                int limiteCheque = sc.nextInt();

                System.out.println("\nInforme o valor da Taxa de Administra??ao: ");
                int taxaAdm = sc.nextInt();

                // gerando a conta corrente
                ContaCorrente newContaC = new ContaCorrente(clienteConta.size() + 1, senha, agencia, limiteCheque, taxaAdm);
                newContaC.setAgencia(agencia);
                ClienteConta cc = new ClienteConta(cliente, newContaC);
                // salvando no banco de dados
                clienteConta.add(cc);

                JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!\n" + "N??mero da conta: " + newContaC.getNumeroConta() + "\nSenha: " + newContaC.getSenha());
                System.out.println("\nConta cadastrada com sucesso! \nPressione enter para continuar...");
                break;

            case 1: // Conta poupan??a

                System.out.println("Digite a senha de 4 numeros da conta");
                senha = sc.nextInt();

                agencia = escolheAgencia();

                // gerando a conta poupan??a
                ContaPoupanca newContaP = new ContaPoupanca(clienteConta.size() + 1, senha, agencia);
                newContaP.setAgencia(agencia);
                ClienteConta cp = new ClienteConta(cliente, newContaP);
                // adicionando ao banco de dados
                clienteConta.add(cp);

                JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!\n" + "N??mero da conta: " + newContaP.getNumeroConta() + "\nSenha: " + newContaP.getSenha());
                System.out.println("Conta cadastrada com sucesso! \nPressione enter para continuar...");

                break;

            case 2: // conta salario

                agencia = escolheAgencia();

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

                JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!\n" + "N??mero da conta: " + newContaS.getNumeroConta() + "\nSenha: " + newContaS.getSenha());
                System.out.println("Conta cadastrada com sucesso! \nPressione enter para continuar...");
                break;

            case 3:
                initialize();
                break;
        }
    }

    public static void createFuncionarios(ArrayList<Funcionario> funcionarios){
        //cadastrar funcionarios
    }

    public static void createAgencias(Agencia[] agencias) {

        Agencia agencia1 = new Agencia(
                123,
                "Agencia Santa Monica",
                new Endereco("Santa Monica", "Uberl??ndia", "Minas Gerais"),
                new Gerente("040.058.140-00", "Josimar", "19.305.188-6", new Date(1999, 12, 04),
                        new Endereco("Uberlandia", "Minas Gerais", "Santa Monica"), "10564786981", 'M', "casado",
                        19.000, CurrentDate.getCurrentDate(), 1));

        Agencia agencia2 = new Agencia(
                321,
                "Agencia Cap??o Redondo",
                new Endereco("Cap??o Redondo", "S??o Paulo", "S??o Paulo"),
                new Gerente("363.995.940-06", "Francisca", "29.083.447-8", new Date(1980, 2, 26),
                        new Endereco("Sao Paulo", "Sao Paulo", "Cap??o Redondo"), "50719719736", 'F', "solteira", 19.000,
                        CurrentDate.getCurrentDate(), 1));

        agencias[0] = agencia1;
        agencias[1] = agencia2;
    }

    // ************************* OPERA????ES BANC??RIAS ************************* //

    public static void efetuaSaque(Conta conta){
        Scanner sc = new Scanner(System.in);
        int verifyError = -1, senha;
        double valor;

        while (verifyError != 0) {
            System.out.println("Insira o valor do saque: ");
            valor = sc.nextDouble();

            String meio = escolheMeio();

            try {
                conta.sacar(valor, meio);
                verifyError = 0;

            } catch (SemSaldoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println(e.getMessage());
                break;

            } catch (ContaDesativadaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println(e.getMessage());
                break;

            } catch (ValorInvalidoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println(e.getMessage());
                verifyError = 3;

            }

            if (verifyError == 0) {
                JOptionPane.showMessageDialog(null, "Opera????o Efetuada com sucesso! ");
                System.out.println("Opera????o Efetuada com sucesso! \nPressione enter para continuar...");
            }
        }
    }
    public static void efetuaDeposito(Conta conta){
        Scanner sc = new Scanner(System.in);
        int verifyError = -1, senha;
        double valor;

        while (verifyError != 0) {

            System.out.println("Insira o valor do dep??sito: ");
            valor = sc.nextDouble();

            String meio = escolheMeio();

            try {
                conta.depositar(valor, meio);
                verifyError = 0;

            } catch (ContaDesativadaException e) {
                System.out.println(e.getMessage());
                verifyError = 1;

            } catch (ValorInvalidoException e) {
                System.out.println(e.getMessage());
                verifyError = 2;

            }

            if (verifyError == 0) {
                JOptionPane.showMessageDialog(null, "Opera????o Efetuada com sucesso! ");
                System.out.println("Opera????o Efetuada com sucesso! \nPressione enter para continuar...");
            }
        }
    }

    public static void efetuaPagamento(Conta conta){
        Scanner sc = new Scanner(System.in);
        int verifyError = -1, senha;
        double valor;

        while (verifyError != 0) {

            System.out.println("Insira o valor do pagamento: ");
            valor = sc.nextDouble();

            System.out.println("Insira a data para efetuar o pagamento no formato dia/mes/ano: ");
            Date dataPagamento = getFormateDate();
            String meio = escolheMeio();

            try {
                conta.efetuarPagamento(valor, meio, dataPagamento);
                verifyError = 0;

            } catch (ContaDesativadaException e) {
                System.out.println(e.getMessage());
                verifyError = 2;

            } catch (SemSaldoException e) {

                if (conta.getSaldoAtual() == 0)
                    break;

                else {
                    verifyError = 2;
                    System.out.println("Seu saldo atual ?? insuficiente. Verifique e tente novamente.");
                }

                verifyError = 3;

            } catch (ValorInvalidoException e) {
                System.out.println(e.getMessage());
                verifyError = 2;

            }

            if (verifyError == 0) {
                JOptionPane.showMessageDialog(null, "Opera????o Efetuada com sucesso! ");
                System.out.println("Opera????o realizada com sucesso! \nPressione enter para continuar...");
            }
        }

    }

    public static void consultarSaldo(Conta conta){
        Scanner sc = new Scanner(System.in);
        int verifyError = -1, senha;
        double valor;

        String meio = escolheMeio();

        while (verifyError != 0) {

            try {
                conta.consultarSaldo(meio);
                verifyError = 0;

            } catch (ContaDesativadaException e) {
                System.out.println(e.getMessage());
                verifyError = 1;
            }

            if (verifyError == 0) {
                System.out.println("Pressione enter para continuar...");
            }
        }
    }


    // ************************* FUN????ES AUXILIARES ************************* //

    public static String escolheMeio() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o meio da transa????o:\n [1] Caixa F??sico\n [2] Caixa Eletr??nico\n [3] Internet Banking");

        int option = sc.nextInt();

        while (option < 1 || option > 3) {
            System.out.println("Escolha inv??lida. Escolha novamente o meio da transa????o:\n [1] Caixa F??sico\n [2] Caixa Eletr??nico\n [3] Internet Banking");
            option = sc.nextInt();
        }

        String meio = null;

        if (option == 1) {
            meio = "Caixa F??sico";

        } else if (option == 2) {
            meio = "Caixa Eletr??nico";

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

    public static void clearPrompt() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Conta confirmaSenhaEConta() {
        boolean senhaOK = false, contaOK = false;
        int senha, numConta;
        Conta conta = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o numero da conta e a senha: ");

        while (!senhaOK || !contaOK) {

            if(!contaOK){
                System.out.printf("Numero da conta: ");
                numConta = sc.nextInt();

                for (ClienteConta c : clienteConta) {//procura a conta informada
                    if (c.getConta().getNumeroConta() == numConta) {
                        contaOK = true;
                        conta = c.getConta();
                        break;
                    }
                }
            }

            if(!senhaOK && contaOK){
                System.out.printf("Senha: ");
                senha = sc.nextInt();

                try {
                    conta.confirmaSenha(senha);
                    senhaOK = true;

                } catch (SenhaIncorretaException e) {
                    System.out.println("Senha incorreta! Verifique e tente novamente.");
                    senhaOK = false;
                }
            }
        }

        return conta;
    }

    public static Agencia escolheAgencia(){
        boolean verify = false;
        Agencia agencia = null;

        //System.out.println("\nSelecione a agencia mais proxima da sua casa: ");
        while (!verify) {

            Object[] possibleValues = { agencias[0].getNome(), agencias[1].getNome()};
            Object selectedValue = JOptionPane.showInputDialog(null,
                    "Selecione a agencia mais proxima da sua casa: \"", "Input",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    possibleValues, possibleValues[0]);

            for (Agencia a : agencias) {// retornando a agencia escolhida e parando o la??o
                if (selectedValue == a.getNome()) {
                    agencia = a;
                    verify = true;
                    break;
                }
            }

            if (agencia == null) {
                System.out.printf("\nAgencia nao encontrada! Verifique e tente novamente. \n");
            }
        }

        return agencia;
    }

}