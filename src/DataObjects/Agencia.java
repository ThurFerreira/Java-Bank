package DataObjects;

import Entidades.Funcionario.Gerente;
import Conta.Conta;
import javax.imageio.ImageReader;
import java.util.HashSet;
import java.util.Set;

public class Agencia {

    private int numero;
    private String nome;
    private String cidade;
    private String estado;
    private String bairro;
    private Gerente gerente;
    private Set<Conta> contas;

    public Agencia(int numero, String nome, String cidade, String estado, String bairro, Gerente gerente, Set<Conta> contas) {
        this.numero = numero;
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.gerente = gerente;
        this.contas = contas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}

