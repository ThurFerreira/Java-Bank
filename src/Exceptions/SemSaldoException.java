package Exceptions;

public class SemSaldoException extends Exception{

    @Override
    public String getMessage() {
        return "Saldo Insuficiente para realizar a transação, verifique e tente novamente!";
    }
}
