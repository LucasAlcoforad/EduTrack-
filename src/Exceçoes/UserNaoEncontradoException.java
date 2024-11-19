package Exceçoes;

public class UserNaoEncontradoException extends RuntimeException{
    public UserNaoEncontradoException(String message) {
        super(message);
    }
}
