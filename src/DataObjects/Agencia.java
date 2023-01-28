package DataObjects;

import Entidades.Funcionario.Funcionario;
import Entidades.Funcionario.Gerente;
import Conta.Conta;
import javax.imageio.ImageReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Agencia implements Serializable {

    private int numeroAgencia;
    private String nome;
    private Endereco endereco;
    private Gerente gerente;
    private List<Conta> contas;
    private List<Funcionario> funcionarios;

    public Agencia(int numeroAgencia, String nome, Endereco endereco, Gerente gerente) {
        this.numeroAgencia = numeroAgencia;
        this.nome = nome;
        this.endereco = endereco;
        this.gerente = gerente;
        this.contas = new ArrayList<Conta>();
        this.funcionarios = new ArrayList<Funcionario>();
    }

    public int getNumero() {
        return numeroAgencia;
    }

    public void setNumero(int numero) {
        this.numeroAgencia = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContaToAgencia(Conta newConta){
        contas.add(newConta);
    }
}

