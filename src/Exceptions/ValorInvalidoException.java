package Exceptions;

public class ValorInvalidoException extends Exception {

    @Override
    public String getMessage() {
        return "Valor de depósito inválido! Verifique e tente novamente.";
    }
}
