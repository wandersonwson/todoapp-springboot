package dev.wson.todoapp.excecoes;

public class ExcecaoGenerica extends RuntimeException {
    public ExcecaoGenerica(String mensagem) {
        super(mensagem);
    }
}