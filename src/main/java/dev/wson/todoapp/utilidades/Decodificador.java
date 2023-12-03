package dev.wson.todoapp.utilidades;

import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class Decodificador {
    public String[] decodificarCredenciais(String autorizacao) {
        String dadosCodificados = autorizacao.split("\s")[1];
        byte[] dadosDecodificados = Base64.getDecoder().decode(dadosCodificados);
        String dadosString = new String(dadosDecodificados);
        String[] credenciais = dadosString.split(":");
        return credenciais;
    }
}
