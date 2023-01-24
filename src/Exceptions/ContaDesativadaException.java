package Exceptions;

public class ContaDesativadaException extends Exception{

    @Override
    public String getMessage() {
        return "Conta Desativada, verifique e tente novamente";
    }
}
