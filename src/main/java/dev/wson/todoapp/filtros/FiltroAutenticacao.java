package dev.wson.todoapp.filtros;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import dev.wson.todoapp.modelos.Usuario;
import dev.wson.todoapp.repositorios.UsuarioRepositorio;
import dev.wson.todoapp.utilidades.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroAutenticacao extends OncePerRequestFilter {
    
    @Autowired
    private Criptografia criptografia;
    @Autowired
    private Decodificador decodificador;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {        
        String caminhoRota = request.getServletPath();
        if (caminhoRota.equals("/tarefa")) {
            String autorizacao = request.getHeader("Authorization");
            String[] credenciais = decodificador.decodificarCredenciais(autorizacao);
            Optional<Usuario> usuarioOptional = usuarioRepositorio.findByEmail(credenciais[0]);
            if (usuarioOptional.isEmpty()) {
                response.sendError(400, "Usuário não encontrado");
            } else {
                String cifra = usuarioOptional.get().getSenha();
                boolean senhaValida = criptografia.validarSenha(credenciais[1], cifra);
                if (!senhaValida) {
                    response.sendError(401, "Acesso não autorizado, verifique a senha");
                } else {
                    UUID usuarioId = usuarioOptional.get().getId();
                    request.setAttribute("usuarioId", usuarioId);
                    filterChain.doFilter(request, response);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }    
    }
}