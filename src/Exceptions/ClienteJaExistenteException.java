package Exceptions;

public class ClienteJaExistenteException extends Exception {

    @Override
    public String getMessage() {
        return "O cpf informado ja está cadastrado";
    }
}
