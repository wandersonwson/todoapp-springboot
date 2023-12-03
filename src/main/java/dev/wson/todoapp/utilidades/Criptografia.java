package dev.wson.todoapp.utilidades;

import org.springframework.stereotype.Component;
import at.favre.lib.crypto.bcrypt.BCrypt;

@Component
public class Criptografia {
    public String cifrarSenha(String senha) {
        String cifra = BCrypt.withDefaults().hashToString(12, senha.toCharArray());
        return cifra;
    }
    public boolean validarSenha(String senha, String cifra) {
        BCrypt.Result resultado = BCrypt.verifyer().verify(senha.toCharArray(), cifra);
        return resultado.verified;
    }
}