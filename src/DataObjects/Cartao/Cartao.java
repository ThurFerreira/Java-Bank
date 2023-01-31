package DataObjects.Cartao;

import java.io.Serializable;

public abstract class Cartao implements Serializable {

    private int numeroCartao;
    private int senhaCartao;
    private String nivel; //1 - basic, 2 - classic, 3 - premium

    public Cartao(String nivel, int numeroCartao, int senhaCartao){
        this.nivel = nivel;
        this.numeroCartao = numeroCartao;
        this.senhaCartao = senhaCartao;
    }
    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Numero do Cartao: " + numeroCartao + ", Nivel Do Cartao: " + nivel;
    }
}
