package DataObjects.Cartao;

public class CartaoDebito extends Cartao {

    int pontosDoCartao;

    public CartaoDebito(String  nivel, int numeroCartao, int senhaCartao) {
        super(nivel, numeroCartao, senhaCartao);
        this.pontosDoCartao = 0;
    }

    public int getPontosDoCartao() {
        return pontosDoCartao;
    }

    public void setPontosDoCartao(int pontosDoCartao) {
        this.pontosDoCartao = pontosDoCartao;
    }

    @Override
    public String toString() {
        return super.toString() + ", Pontos do Cartao: " + pontosDoCartao;
    }
}
