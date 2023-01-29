package DataObjects;

import Conta.Conta;
import Exceptions.SenhaIncorretaException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransacaoBancaria implements Serializable{
    private double valorTransacao;
    private int tipoDaTransacao;//1 saque; 2 deposito; 3 consultar saldo; 4 efetuar pagamento
    private Date dataDaTransacao;
    private String canalPorOndeFoiFeita;

    public TransacaoBancaria(double valorTransacao, int tipoDaTransacao, String meio) {
        this.valorTransacao = valorTransacao;
        this.tipoDaTransacao = tipoDaTransacao;
        this.dataDaTransacao = CurrentDate.getCurrentDate();
        this.canalPorOndeFoiFeita = meio;
    }

    public TransacaoBancaria(double valorTransacao, int tipoDaTransacao, String meio, Date dataPagamento) {
        this.valorTransacao = valorTransacao;
        this.tipoDaTransacao = tipoDaTransacao;
        this.dataDaTransacao = dataPagamento;
        this.canalPorOndeFoiFeita = meio;
    }

    public double getValorTransacoesBancariasTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
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

    public void setCanalPorOndeFoiFeita(String meio) {
        this.canalPorOndeFoiFeita = canalPorOndeFoiFeita;
    }

    @Override
    public String toString() {
        return "TransacaoBancaria [valorTransacao=" + valorTransacao + ", tipoDaTransacao=" + tipoDaTransacao
                + ", dataDaTransacao=" + dataDaTransacao + ", canalPorOndeFoiFeita=" + canalPorOndeFoiFeita + "]";
    }
}
