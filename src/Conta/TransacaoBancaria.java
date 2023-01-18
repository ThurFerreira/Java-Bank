package Conta;

import Exceptions.SenhaIncorretaException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransacaoBancaria implements TransacoesBancarias{

    private Conta conta;
    private int valorTransacao;
    private int tipoDaTransacao;//1 saque; 2 deposito; 3 consultar saldo; 4 efetuar pagamento
    private String dataDaTransacao;
    private String canalPorOndeFoiFeita;
    public TransacaoBancaria(Conta conta, String data){
        this.conta = conta;
        conta.setUltimaMovimentacao(data);
    }

    public int getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(int valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public String getDataDaTransacao() {
        return dataDaTransacao;
    }

    public void setDataDaTransacao(String dataDaTransacao) {
        this.dataDaTransacao = dataDaTransacao;
    }

    public String getCanalPorOndeFoiFeita() {
        return canalPorOndeFoiFeita;
    }

    public void setCanalPorOndeFoiFeita(String canalPorOndeFoiFeita) {
        this.canalPorOndeFoiFeita = canalPorOndeFoiFeita;
    }

    private String getCurrentDate() {
        //getting and formatting the current account creation date
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");//format patter
        Date date = new Date(System.currentTimeMillis());//System.currentTimeMillis() get the current date
        return formatter.format(date);
    }

    @Override
    public void sacar(int senha, int valor) throws SenhaIncorretaException {
        confirmaSenha(senha);;
    }

    @Override
    public void depositar(int senha, int valor) {

    }

    @Override
    public int consultarSaldo(int senha) {
        return 0;
    }

    @Override
    public void efeturarPagamento(int senha, int valor, String data) {

    }

    private void confirmaSenha(int senhaDigitada) throws SenhaIncorretaException {
        if(conta.getSenha() != senhaDigitada){
            throw new SenhaIncorretaException();
        }
    }
}