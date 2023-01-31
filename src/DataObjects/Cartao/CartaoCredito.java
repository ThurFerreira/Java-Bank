package DataObjects.Cartao;

public class CartaoCredito extends Cartao{

    double limite;

    public CartaoCredito(String nivel, int numeroCartao, double limite, int senhaCartao) {
        super(nivel, numeroCartao, senhaCartao);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return super.toString() +  ", Limite: " + limite;
    }
}
