package Exceptions;

public class SenhaIncorretaException extends Exception{

    @Override
    public String getMessage() {
        return "Senha Incorreta! Tente novamente.";
    }
}
