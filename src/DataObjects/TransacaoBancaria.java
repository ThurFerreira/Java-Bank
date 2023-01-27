package DataObjects;

import Conta.Conta;
import Exceptions.SenhaIncorretaException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransacaoBancaria{
    private int valorTransacao;
    private int tipoDaTransacao;//1 saque; 2 deposito; 3 consultar saldo; 4 efetuar pagamento
    private Date dataDaTransacao;
    private String canalPorOndeFoiFeita;

    public TransacaoBancaria(int valorTransacao, int tipoDaTransacao, String canalPorOndeFoiFeita) {
        this.valorTransacao = valorTransacao;
        this.tipoDaTransacao = tipoDaTransacao;
        this.dataDaTransacao = CurrentDate.getCurrentDate();
        this.canalPorOndeFoiFeita = canalPorOndeFoiFeita;
    }

    public int getValorTransacoesBancariasTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(int valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public Date getDataDaTransacao() {
        return dataDaTransacao;
    }

    public void setDataDaTransacao(Date dataDaTransacao) {
        this.dataDaTransacao = dataDaTransacao;
    }

    public String getCanalPorOndeFoiFeita() {
        return canalPorOndeFoiFeita;
    }

    public void setCanalPorOndeFoiFeita(String canalPorOndeFoiFeita) {
        this.canalPorOndeFoiFeita = canalPorOndeFoiFeita;
    }
}
