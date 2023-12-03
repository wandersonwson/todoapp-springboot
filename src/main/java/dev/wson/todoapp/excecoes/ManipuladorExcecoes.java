package dev.wson.todoapp.excecoes;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManipuladorExcecoes {
    
    private Map<String, String> erros;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarExcecaoValidacao(MethodArgumentNotValidException e) {
        erros = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(erro -> {
            String campo = erro.getField();
            String menssagem = erro.getDefaultMessage();
            erros.put(campo, menssagem);
        });
        return ResponseEntity.status(400).body(erros);
    }
}